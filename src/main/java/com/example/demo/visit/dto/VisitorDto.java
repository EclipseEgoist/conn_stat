package com.example.demo.visit.dto;

public class VisitorDto {
	
	private Long visitSeq; // 기록 순서
	private String visitId; // 로그인 아이디가 존재할 경우
	private String visitIp; // 접속 아이피
	private String visitAddr; // 접속 주소
	private String visitTime; // 해당 컨트롤러 접속 시간
	private String visitRefer; // 이전 경로
	private String visitAgent; // userAgent
	
	private String product; // Mozilla
	private String productV; // 5.0
	private String system; // 접속환경
	private String systemV; // 접속환경 버전
	private String platform; // 플랫폼
	private String platformV; // 플랫폼 버전
	private String ext; // 접속 브라우저
	
	// 검색값
	private String startDate;
	private String endDate;
	// 검색 결과 값
	private String subVisitTime;
	private String countVisit;
	
	public VisitorDto() {
		super();
	}

	public Long getVisitSeq() {
		return visitSeq;
	}

	public void setVisitSeq(Long visitSeq) {
		this.visitSeq = visitSeq;
	}

	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	public String getVisitIp() {
		return visitIp;
	}

	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getVisitRefer() {
		return visitRefer;
	}

	public void setVisitRefer(String visitRefer) {
		this.visitRefer = visitRefer;
	}

	public String getVisitAgent() {
		return visitAgent;
	}

	public void setVisitAgent(String visitAgent) {
		this.visitAgent = visitAgent;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductV() {
		return productV;
	}

	public void setProductV(String productV) {
		this.productV = productV;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getSystemV() {
		return systemV;
	}

	public void setSystemV(String systemV) {
		this.systemV = systemV;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPlatformV() {
		return platformV;
	}

	public void setPlatformV(String platformV) {
		this.platformV = platformV;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getVisitAddr() {
		return visitAddr;
	}

	public void setVisitAddr(String visitAddr) {
		this.visitAddr = visitAddr;
	}

	public String getSubVisitTime() {
		return subVisitTime;
	}

	public void setSubVisitTime(String subVisitTime) {
		this.subVisitTime = subVisitTime;
	}

	public String getCountVisit() {
		return countVisit;
	}

	public void setCountVisit(String countVisit) {
		this.countVisit = countVisit;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
