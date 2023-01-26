package com.imooic.lib_voice.engine;

import java.util.List;

/**
 {"errno":0,"bot_session_list":[{"bot_id":"1253976","bot_session_id":"session-1667196376310-4067501293-8013-496"},{"bot_id":"1067049","bot_session_id":"session-1667196376191-4208950265-8013-5898"},{"bot_session_id":"session-1667196376177-1998191077-8991-7387"}],"unit_response":[{"version":"2.0","timestamp":"2022-10-31 14:06:16.418","response":{"msg":"ok","schema":{"intent_confidence":100,"slots":[{"word_type":"","fuzzy_matches":[],"confidence":100,"length":2,"name":"user_loc","original_word":"广州","sub_slots":[],"session_offset":0,"begin":0,"normalized_word":"广州","merge_method":"ADD"}],"domain_confidence":0,"intent":"USER_WEATHER"},"qu_res":{"candidates":[{"intent_confidence":100,"match_info":"{\"from_who\":\"template\",\"match_template\":{\"template_id\":6788858,\"real_threshold\":1.0,\"threshold\":0.800000011920929,\"match_pattern\":[\"[D:user_loc]\",\"[D:kw_weatherstatus]\"]}}","slots":[{"word_type":"","father_idx":-1,"fuzzy_matches":[],"confidence":100,"length":2,"name":"user_loc","original_word":"广州","begin":0,"need_clarify":false,"normalized_word":"广州"}],"extra_info":{},"confidence":100,"domain_confidence":0,"from_who":"pow-slu-lev1","intent":"USER_WEATHER","intent_need_clarify":false}],"qu_res_chosen":"{\"confidence\":100.0,\"intent\":\"USER_WEATHER\",\"intent_confidence\":100.0,\"domain_confidence\":0.0,\"intent_need_clarify\":false,\"slots\":[{\"confidence\":100.0,\"fuzzy_matches\":[],\"begin\":0,\"original_word\":\"广州\",\"normalized_word\":\"广州\",\"word_type\":\"\",\"name\":\"user_loc\",\"need_clarify\":false,\"father_idx\":-1,\"length\":4}],\"from_who\":\"pow-slu-lev1\",\"match_info\":\"{\\\"from_who\\\":\\\"template\\\",\\\"match_template\\\":{\\\"template_id\\\":6788858,\\\"real_threshold\\\":1.0,\\\"threshold\\\":0.800000011920929,\\\"match_pattern\\\":[\\\"[D:user_loc]\\\",\\\"[D:kw_weatherstatus]\\\"]}}\",\"extra_info\":{}}","sentiment_analysis":{"pval":0.9995063543319702,"label":"1"},"lexical_analysis":[{"etypes":["user_loc","sys_loc_city","sys_loc"],"basic_word":["广州"],"weight":0.2476110607385636,"term":"广州","type":"user_loc"},{"etypes":[],"basic_word":["的"],"weight":0.05074924230575562,"term":"的","type":"33"},{"etypes":[],"basic_word":["天气"],"weight":0.3749890029430389,"term":"天气","type":"21"},{"etypes":[],"basic_word":["怎么"],"weight":0.137950748205185,"term":"怎么","type":"30"},{"etypes":[],"basic_word":["样"],"weight":0.137950748205185,"term":"样","type":"3"},{"etypes":[],"basic_word":["？"],"weight":0.05074924230575562,"term":"？","type":"37"}],"raw_query":"广州的天气怎么样？","status":0,"timestamp":0},"action_list":[{"action_id":"build_weather_satisfy","refine_detail":{"option_list":[],"interact":"","clarify_reason":""},"confidence":100,"custom_reply":"","say":"广州今天晴转阵雨，气温19到29度~外出建议带伞。","type":"satisfy"}],"status":0},"bot_id":"1253976","log_id":"256459411","interaction_id":"interaction-1667196376418-3296611033--3534"},{"version":"2.0","timestamp":"2022-10-31 14:06:16.257","response":{"msg":"ok","schema":{"intent_confidence":0,"slots":[],"domain_confidence":0,"slu_tags":[],"intent":""},"qu_res":{},"action_list":[{"action_id":"fail_action","refine_detail":{"option_list":[],"interact":"","clarify_reason":""},"confidence":100,"custom_reply":"","say":"我不知道该怎么答复您。","type":"failure"}],"status":0},"bot_id":"1253997","log_id":"256459411","interaction_id":"interaction-1667196376257-4208950265-8013-5898"},{"version":"2.0","timestamp":"2022-10-31 14:06:16.485","response":{"msg":"ok","schema":{"intent_confidence":1,"intent":"BUILT_CHAT"},"qu_res":{},"action_list":[{"img":[],"action_id":"chat_satisfy","refine_detail":{},"confidence":-1.698518753051758,"custom_reply":"","say":"这几天下雨,有点冷,多穿","type":"chat"},{"img":[],"action_id":"chat_satisfy","refine_detail":{},"confidence":-1.84049117565155,"custom_reply":"","say":"很热,很闷","type":"chat"},{"img":[],"action_id":"chat_satisfy","refine_detail":{},"confidence":-1.940871834754944,"custom_reply":"","say":"我在广州啊,我也没觉得有多热啊","type":"chat"}],"status":0},"bot_id":"1253998","log_id":"256459411","interaction_id":"interaction-1667196376485-1998191077-8991-9484"},{"version":"2.0","timestamp":"","response":{"msg":"QPS超限，当前QPS限额[1]","status":292003},"bot_id":"1253977","log_id":"256459411","bot_session":"","interaction_id":""}]}
 */
public class UnitParams {

    public int errno;
    public List<BotSessionList> bot_session_list;
    public List<UnitResponse> unit_response;

    public static class BotSessionList {
        public String bot_id;
        public String bot_session_id;
    }

    public static class UnitResponse {
        public String version;
        public String timestamp;
        public Response response;
        public String bot_id;
        public String log_id;
        public String interaction_id;
        public String bot_session;

        public static class Response {
            public String msg;
            public Schema schema;
            public QuRes qu_res;
            public List<ActionList> action_list;
            public int status;

            public static class Schema {
                public int intent_confidence;
                public List<Slots> slots;
                public float domain_confidence;
                public String intent;

                public static class Slots {
                    public String word_type;
                    public List<?> fuzzy_matches;
                    public float confidence;
                    public int length;
                    public String name;
                    public String original_word;
                    public List<?> sub_slots;
                    public int session_offset;
                    public int begin;
                    public String normalized_word;
                    public String merge_method;
                }
            }

            public static class QuRes {
                public List<Candidates> candidates;
                public String qu_res_chosen;
                public SentimentAnalysis sentiment_analysis;
                public List<LexicalAnalysis> lexical_analysis;
                public String raw_query;
                public int status;
                public int timestamp;

                public static class SentimentAnalysis {
                    public double pval;
                    public String label;
                }

                public static class Candidates {
                    public int intent_confidence;
                    public String match_info;
                    public List<Slots> slots;
                    public ExtraInfo extra_info;
                    public float confidence;
                    public int domain_confidence;
                    public String from_who;
                    public String intent;
                    public boolean intent_need_clarify;

                    public static class ExtraInfo {
                    }

                    public static class Slots {
                        public String word_type;
                        public int father_idx;
                        public List<?> fuzzy_matches;
                        public float confidence;
                        public int length;
                        public String name;
                        public String original_word;
                        public int begin;
                        public boolean need_clarify;
                        public String normalized_word;
                    }
                }

                public static class LexicalAnalysis {
                    public List<String> etypes;
                    public List<String> basic_word;
                    public double weight;
                    public String term;
                    public String type;
                }
            }

            public static class ActionList {
                public String action_id;
                public RefineDetail refine_detail;
                public float confidence;
                public String custom_reply;
                public String say;
                public String type;

                public static class RefineDetail {
                    public List<?> option_list;
                    public String interact;
                    public String clarify_reason;
                }
            }
        }
    }
}
