package com.wx.jmeter.dubbo;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wx.provider.DemoService;

public class JmeterDubboLoadTest implements JavaSamplerClient {

	private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			new String[] { "customer.xml" });

	//private static JavaSamplerContext Context;
	private String a;
	private String b;

	@Override
	public Arguments getDefaultParameters() {
		// TODO Auto-generated method stub

		Arguments params = new Arguments();
		params.addArgument("first", "");
		params.addArgument("second", "");
		return params;
	}

	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		// TODO Auto-generated method stub


		SampleResult sr = new SampleResult();
		sr.setSampleLabel("wuxi-test");
		try {
			sr.sampleStart();
			//context.start();
			DemoService demoService = (DemoService) context.getBean("demoService");
			String c = demoService.getStr(a, b);
			sr.setResponseData(c, null);
			sr.setDataType(SampleResult.TEXT);
			sr.setSuccessful(true);
			sr.sampleEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sr;

	}

	@Override
	public void setupTest(JavaSamplerContext arg0) {
		// TODO Auto-generated method stub
		a = arg0.getParameter("first");
		b = arg0.getParameter("second");

	}

	@Override
	public void teardownTest(JavaSamplerContext arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
