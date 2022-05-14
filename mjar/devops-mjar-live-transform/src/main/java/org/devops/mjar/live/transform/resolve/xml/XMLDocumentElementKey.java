package org.devops.mjar.live.transform.resolve.xml;

/**
 * @author GENSEN
 * @date 2021/6/25 17:13
 * @description：节点键值
 */
public interface XMLDocumentElementKey {
    String _method = "method";

    String _transformers = "transformers";

    interface Transformers {
        String _transformer = "transformer";
        String _type = "type";

        interface Transformer {
            String _path = "path";
            String _class = "class";

            String _manual = "manual";

            interface Manual {
                String _target = "target";
                String _request = "request";
                String _response = "response";
                String _field = "field";
                String _from_content_type = "from-content-type";
                String _to_content_type = "to-content-type";

                interface Field {
                    String _from = "from";
                    String _to = "to";
                    String _type = "type";
                    String _mapper = "mapper";
                    String _required = "required";

                    interface Mapper {
                        String _jmapper = "jmapper";
                        String _mapping = "mapping";
                        String _if = "if";
                    }
                }

            }
        }
    }


    String _preprocess = "preprocess";
    String _postprocess = "postprocess";

    interface Process {
        String _processor = "processor";
        String _class = "class";
    }

}
