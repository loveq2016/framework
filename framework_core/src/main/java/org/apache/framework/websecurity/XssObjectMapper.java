package org.apache.framework.websecurity;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.module.SimpleModule;
import org.springframework.web.util.HtmlUtils;

public class XssObjectMapper extends ObjectMapper {

	public XssObjectMapper() {
		SimpleModule module = new SimpleModule("HTML XSS Serializer", new Version(1, 0, 0, "FINAL"));
		module.addSerializer(new JsonHtmlXssSerializer(String.class));
		this.registerModule(module); 
		
		this.getSerializerProvider().setNullValueSerializer(
				new JsonSerializer<Object>() {
					@Override
					public void serialize(Object value, JsonGenerator jgen,
							SerializerProvider provider) throws IOException,
							JsonProcessingException {
						jgen.writeString("");
					}
				});
	}

	class JsonHtmlXssSerializer extends JsonSerializer<String> {
		public JsonHtmlXssSerializer(Class<String> string) {
			super();
		}

		public Class<String> handledType() {
			return String.class;
		}

		public void serialize(String value, JsonGenerator jsonGenerator,
				SerializerProvider serializerProvider) throws IOException,
				JsonProcessingException {
			if (value != null) {
				String encodedValue = HtmlUtils.htmlEscape(value.toString());
				jsonGenerator.writeString(encodedValue);
			}
		}
	}
	
}