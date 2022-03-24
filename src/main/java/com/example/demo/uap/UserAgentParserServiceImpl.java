package com.example.demo.uap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Visitor;
import com.example.demo.visit.service.VisitorService;

@Service
public class UserAgentParserServiceImpl implements UserAgentParserService {
	
	private VisitorService vService;
	
	public UserAgentParserServiceImpl(VisitorService vService) {
		super();
		this.vService = vService;
	}

	@Override
	public void parseRequest(HttpServletRequest request) throws Exception {
		Visitor bean = new Visitor();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Map<String, String > uaMap = parseUA(request.getHeader("User-Agent"));
		
		bean.setVisitId(null);
		bean.setVisitIp(request.getRemoteAddr());
		bean.setVisitAddr(request.getRequestURI());
		bean.setVisitTime(sdf.format(date));
		bean.setVisitRefer(request.getHeader("referer"));
		bean.setVisitAgent(request.getHeader("User-Agent"));
		if(uaMap != null) {
			bean.setProduct(uaMap.get("product"));
			bean.setProductV(uaMap.get("productV"));
			bean.setSystem(uaMap.get("system"));
			bean.setSystemV(uaMap.get("systemV"));
			bean.setPlatform(uaMap.get("platform"));
			bean.setPlatformV(uaMap.get("platformV"));
			bean.setExt(uaMap.get("ext"));
		}
		if(bean.getVisitAgent().toLowerCase().indexOf("java") != 0) {
			System.out.println("bean : " + BeanUtils.describe(bean));
			vService.saveVisitor(bean);
		} else {
			System.out.println("javaBean : " + BeanUtils.describe(bean));
		}
	}

	@Override
	public Map<String, String> parseUA(String ua) {
		// Mozilla/5.0 (<system-information>) <platform> (<platform-details>) <extensions>
		ua = ua.toLowerCase();
		if(ua.indexOf("java") == 0) {
			return null;
		}
		
		String[] tmpUA = ua.split("/");
		Map<String, String> map = new HashMap<String, String>();
		String product = "";
		String productVersion = "";
		String system = "";
		String systemVersion1 = "";
		String systemVersion2 = "";
		String platform = "";
		String platformVersion = "";
		String platformDtl = "";
		StringBuffer ext = new StringBuffer();
		
		// windows, ie가 아닐 경우
		if(ua.indexOf("wow") == -1) {
			for(int i = 0; i < tmpUA.length; i++) {
				if(i == 0) {
					product = tmpUA[i];
					map.put("product", product);
					System.out.println("product : " + product);
				}
				if(i == 1) {
					int idx = tmpUA[i].indexOf(" ");
					productVersion = tmpUA[i].substring(0, idx).trim();
					map.put("productV", productVersion);
					System.out.println("productVersion : " + productVersion);
					int sIdx = tmpUA[i].indexOf("(");
					int eIdx = tmpUA[i].indexOf(")");
					String tmpSystem = "";
					String[] tmpSysArr;
					if(sIdx != -1 || eIdx != -1) {
						tmpSystem = tmpUA[i].substring(sIdx+1, eIdx);
						System.out.println("tmpSys : " + tmpSystem);
						tmpSysArr = tmpSystem.split(";");
						for(int j = 0; j < tmpSysArr.length; j++) {
							if(j == 0) {
								system = tmpSysArr[j].trim();
								map.put("system", system);
								System.out.println("system : " + system);
							} else if (j == 1) {
								systemVersion1 = tmpSysArr[j].trim();
								System.out.println("systemVersion1 : " + systemVersion1);
							} else if (j == 2) {
								systemVersion2 = tmpSysArr[j].trim();
								System.out.println("systemVersion2 : " + systemVersion2);
							}
						}
						map.put("systemV", systemVersion1 + " " + systemVersion2);
						platform = tmpUA[i].substring(eIdx+1).trim();
						map.put("platform", platform);
						System.out.println("platform : " + platform);
					}
				}
				if(i == 2) {
					int idx = tmpUA[i].indexOf(" ");
					platformVersion = tmpUA[i].substring(0, idx).trim();
					map.put("platformV", platformVersion);
					System.out.println("platformVersion : " + platformVersion);
					int sIdx = tmpUA[i].indexOf("(");
					int eIdx = tmpUA[i].indexOf(")");
					if(sIdx != -1 || eIdx != -1) {
						platformDtl = tmpUA[i].substring(sIdx+1, eIdx);
						System.out.println("platformDtl : " + platformDtl);
						if(StringUtils.isNotEmpty(tmpUA[i].substring(eIdx+1).trim())) {
							ext.append(tmpUA[i].substring(eIdx+1).trim());
							System.out.println("ext : " + ext);
						}
					}
				}
				if(i > 2) {
					if(StringUtils.isNotEmpty(tmpUA[i])) {
						ext.append(tmpUA[i] == null ? "" : tmpUA[i]);
						System.out.println("tmpUA["+i+"] : " + tmpUA[i]);
						System.out.println("ext : " + ext);
					}
					map.put("ext", ext.toString());
				}
			}
		} else {
			// windows, ie일 경우
		}
		return map;
	}
	
}
