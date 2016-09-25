

import org.apache.framework.generator.Generator;

public class Test { 

	public static void main(String[] args) {
		Generator gen = new Generator();
		gen.execute(Test.class.getResourceAsStream("/coupon_config.xml"));
	}

}
