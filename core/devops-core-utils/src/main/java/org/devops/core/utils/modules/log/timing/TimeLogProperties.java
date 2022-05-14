package org.devops.core.utils.modules.log.timing;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class TimeLogProperties {

	@Value("${timeLog.enable: false}")
	private  boolean enable;
}
