package org.devops.mjar.live.polyv.pojo.param;

import org.devops.mjar.live.core.operator.ParameterBuilder;
import org.devops.mjar.live.core.sign.ChannelSignBean;
import lombok.Data;
import lombok.Getter;

/**
 * 修改频道播放器中显示的logo
 */
@Data
public class PolyvChannelLogoParameter extends ChannelSignBean {


    private String logoImage;

    private Float logoOpacity;

    private String logoPosition;

    private String logoHref;

    @Getter
    public enum Position{
        TL( "tl","左上角" ),
        TR( "tr","右上角" ),
        BL( "bl","左下角" ),
        BR( "br","右下角" );

        Position( String code,String value ){
            this.code = code;
            this.value = value;
        }

        private String code;
        private String value;
    }

    public static final class PolyvChannelLogoParameterBuilder extends ParameterBuilder<PolyvChannelLogoParameter> {
        private PolyvChannelLogoParameter polyvChannelLogoParameter;

        private PolyvChannelLogoParameterBuilder() {
            polyvChannelLogoParameter = new PolyvChannelLogoParameter();
        }

        public static PolyvChannelLogoParameterBuilder aPolyvChannelLogoParameter() {
            return new PolyvChannelLogoParameterBuilder();
        }

        public PolyvChannelLogoParameterBuilder withLogoImage(String logoImage) {
            polyvChannelLogoParameter.setLogoImage(logoImage);
            return this;
        }

        public PolyvChannelLogoParameterBuilder withLogoOpacity(Float logoOpacity) {
            polyvChannelLogoParameter.setLogoOpacity(logoOpacity);
            return this;
        }

        public PolyvChannelLogoParameterBuilder withLogoPosition(Position logoPosition) {
            polyvChannelLogoParameter.setLogoPosition(logoPosition.getCode());
            return this;
        }

        public PolyvChannelLogoParameterBuilder withLogoHref(String logoHref) {
            polyvChannelLogoParameter.setLogoHref(logoHref);
            return this;
        }

        @Override
        public PolyvChannelLogoParameter build() {
            return polyvChannelLogoParameter;
        }
    }
}
