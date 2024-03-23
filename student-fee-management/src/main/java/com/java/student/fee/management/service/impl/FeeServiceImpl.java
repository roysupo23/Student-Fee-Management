package com.java.student.fee.management.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.java.student.fee.management.common.ErrorCode;
import com.java.student.fee.management.entity.FeeEntity;
import com.java.student.fee.management.entity.StudentDetailsEntity;
import com.java.student.fee.management.exception.DataMismatchException;
import com.java.student.fee.management.exception.NotFoundException;
import com.java.student.fee.management.model.FeeRequest;
import com.java.student.fee.management.model.FeeResponse;
import com.java.student.fee.management.model.FeesType;
import com.java.student.fee.management.repo.FeeRepo;
import com.java.student.fee.management.repo.StudentDetailsRepo;
import com.java.student.fee.management.service.FeeService;

@Service
public class FeeServiceImpl implements FeeService {

	Logger logger = LoggerFactory.getLogger(FeeServiceImpl.class);
	
	@Value("{app.support.contact.email}")
	private String supporEmail;

	@Autowired
	ModelMapper mapper;

	@Autowired
	FeeRepo feeRepo;

	@Autowired
	StudentDetailsRepo studentDetailsRepo;

	@Transactional
	@Override
	public FeeResponse collectFee(FeeRequest feeRequest) throws NotFoundException, DataMismatchException {

		logger.info("Entering collectFee of {}", this.getClass().getSimpleName());

		StudentDetailsEntity studentDetailsEntity = studentDetailsRepo.findById(feeRequest.getStudentId())
				.orElseThrow(() -> new NotFoundException(ErrorCode.STUDENT_NOT_FOUND));

		if (!StringUtils.equalsIgnoreCase(feeRequest.getStudentName(), studentDetailsEntity.getName())) {
			throw new DataMismatchException(ErrorCode.STUDENT_DATA_MISMATCH);
		}

		String referenceNo = getReferenceNo();
		List<FeeEntity> feeEntityList = feeRequest.getFeetypeList().stream().map(ft -> {
			FeeEntity feeEntity = mapper.map(feeRequest, FeeEntity.class);
			mapper.map(ft, feeEntity);
			mapper.map(studentDetailsEntity, feeEntity);
			feeEntity.setId(null);
			feeEntity.setReferenceNo(referenceNo);
			feeEntity.setTimestamp(new Date());
			return feeEntity;
		}).toList();

		feeEntityList = feeRepo.saveAll(feeEntityList);

		FeeResponse feeResponse = mapper.map(studentDetailsEntity, FeeResponse.class);
		mapper.map(feeEntityList.get(0), feeResponse);
		feeResponse.setFeesTypeList(feeRequest.getFeetypeList());

		logger.info("Exiting collectFee of {}", this.getClass().getSimpleName());
		return feeResponse;
	}

	private String getReferenceNo() {

		logger.info("Entering getReferenceNo of {}", this.getClass().getSimpleName());

		String uuid = String.valueOf(UUID.randomUUID());
		Integer count = feeRepo.countRefernceNo(uuid);
		if (count > 0) {
			uuid = getReferenceNo();
		}

		logger.info("Exiting getReferenceNo of {}", this.getClass().getSimpleName());
		return String.valueOf(uuid);
	}

	@Override
	public InputStreamResource downloadReciept(String referenceNo, String fileName)
			throws DocumentException, IOException, NotFoundException {

		logger.info("Entering downloadReciept of {}", this.getClass().getSimpleName());

		List<FeeEntity> feeEntityList = feeRepo.findByReferenceNo(referenceNo);
		if (CollectionUtils.isEmpty(feeEntityList)) {
			throw new NotFoundException(ErrorCode.INVALID_REFERENCE_NUMBER, referenceNo);
		}

		FeeEntity feeEntity = feeEntityList.get(0);

		StudentDetailsEntity studentDetailsEntity = studentDetailsRepo.findById(feeEntity.getStudentId())
				.orElse(new StudentDetailsEntity());

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, byteArrayOutputStream);

		document.open();

		Image img = Image.getInstance(ResourceUtils.getURL("src\\main\\resources\\photo\\skiplysmall.jpg"));
//		img.setAbsolutePosition(20f, 10f);
		document.add(img);

		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(14);

		Paragraph paragraph = new Paragraph("Your transaction is successfull", fontTiltle);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);

		StringBuilder sb = new StringBuilder();
		sb.append("Hi ");
		sb.append(feeEntity.getPayee());
		sb.append(",");
		Paragraph paragraph1 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph1);

		sb = new StringBuilder();
		sb.append("Congratulations! ");
		sb.append("Your payment transaction is successfull with ");
		sb.append(feeEntity.getSchoolName());
		sb.append(" for ");
		sb.append(feeEntity.getStudentName());
		sb.append("student of ");
		sb.append(studentDetailsEntity.getGrade());
		Paragraph paragraph2 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph2);

		sb = new StringBuilder();
		sb.append("Kindly find the details below: ");
		Paragraph paragraph3 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph3);
		
		sb = new StringBuilder();
		sb.append("Transaction Details: ");
		Paragraph paragraph4 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph4);
		
		img = Image.getInstance(ResourceUtils.getURL("src\\main\\resources\\photo\\skiplybig.jpg"));
//		img.setAbsolutePosition(20f, 10f);
		document.add(img);
		
		sb = new StringBuilder();
		sb.append("> Date and Time: ");
		sb.append(feeEntity.getTimestamp());
		Paragraph paragraph5 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph5);
		
		sb = new StringBuilder();
		sb.append("> Student Name: ");
		sb.append(feeEntity.getStudentName());
		Paragraph paragraph6 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph6);
		
		sb = new StringBuilder();
		sb.append("> Student ID: ");
		sb.append(feeEntity.getStudentId());
		Paragraph paragraph7 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph7);
		
		sb = new StringBuilder();
		sb.append("> Reference #: ");
		sb.append(feeEntity.getReferenceNo());
		Paragraph paragraph8 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph8);

		sb = new StringBuilder();
		sb.append("> Card #: ");
		sb.append(feeEntity.getCardNo());
		Paragraph paragraph9 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph9);
		
		sb = new StringBuilder();
		sb.append("> Card Type: ");
		sb.append(feeEntity.getCardType());
		Paragraph paragraph10 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph10);
		
		sb = new StringBuilder();
		sb.append("Purchase Details: ");
		Paragraph paragraph11 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph11);
		
		String blankString = "       ";
		for (FeeEntity fe : feeEntityList) {
			sb = new StringBuilder();
			sb.append(fe.getCategory());
			sb.append(blankString);
			sb.append(fe.getCount());
			sb.append(blankString);
			sb.append(fe.getCurrency());
			sb.append(blankString);
			sb.append(fe.getAmount());
			Paragraph paragraph12 = new Paragraph(sb.toString(), fontTiltle);
			document.add(paragraph12);
		}
		
		Double totalAmount = feeEntityList.stream().mapToDouble(FeeEntity::getAmount).sum();
		
		sb = new StringBuilder();
		sb.append("Total ");
		sb.append(blankString);
		sb.append(feeEntity.getCurrency());
		sb.append(blankString);
		sb.append(totalAmount);
		Paragraph paragraph13 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph13);
		
		sb = new StringBuilder();
		sb.append("This is an automated email, please do not reply. For any other query, please email us at ");
		sb.append(supporEmail);
		Paragraph paragraph14 = new Paragraph(sb.toString(), fontTiltle);
		document.add(paragraph14);
		
		document.close();

		File file = new File(fileName);
		FileUtils.writeByteArrayToFile(file, byteArrayOutputStream.toByteArray());
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		logger.info("Exiting downloadReciept of {}", this.getClass().getSimpleName());
		return resource;

	}

	@Override
	public FeeResponse viewReciept(String referenceNo) throws NotFoundException {

		logger.info("Entering viewReciept of {}", this.getClass().getSimpleName());

		List<FeeEntity> feeEntityList = feeRepo.findByReferenceNo(referenceNo);

		if (CollectionUtils.isEmpty(feeEntityList)) {
			throw new NotFoundException(ErrorCode.INVALID_REFERENCE_NUMBER, referenceNo);
		}

		FeeEntity feeEntity = feeEntityList.get(0);
		StudentDetailsEntity studentDetailsEntity = studentDetailsRepo.findById(feeEntity.getStudentId())
				.orElse(new StudentDetailsEntity());

		FeeResponse feeResponse = mapper.map(studentDetailsEntity, FeeResponse.class);
		mapper.map(feeEntityList.get(0), feeResponse);
		List<FeesType> feetypeList = feeEntityList.stream().map(fe -> mapper.map(fe, FeesType.class)).toList();
		feeResponse.setFeesTypeList(feetypeList);

		logger.info("Exiting viewReciept of {}", this.getClass().getSimpleName());
		return feeResponse;
	}

}
