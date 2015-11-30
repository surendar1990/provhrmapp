package com.prov.hrm.utility;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;




import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonDateSerializer extends JsonSerializer<Date>{
	 private static SimpleDateFormat formatter = 
		      new SimpleDateFormat("dd-MM-yyyy");
		 


			public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
					throws IOException,
					com.fasterxml.jackson.core.JsonProcessingException {
				 gen.writeString(formatter.format(value));
				
			}
}
