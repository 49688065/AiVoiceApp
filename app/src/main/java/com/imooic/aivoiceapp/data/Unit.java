package com.imooic.aivoiceapp.data;

import com.google.gson.Gson;

import java.util.List;

public class Unit {
    private String version;
    private String timestamp;
    private Response response;
    private String bot_id;
    private String log_id;
    private String interaction_id;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getBot_id() {
        return bot_id;
    }

    public void setBot_id(String bot_id) {
        this.bot_id = bot_id;
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public String getInteraction_id() {
        return interaction_id;
    }

    public void setInteraction_id(String interaction_id) {
        this.interaction_id = interaction_id;
    }

    public static class Response {
        private String msg;
        private Schema schema;
        private QuRes qu_res;
        private List<ActionList> action_list;
        private int status;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Schema getSchema() {
            return schema;
        }

        public void setSchema(Schema schema) {
            this.schema = schema;
        }

        public QuRes getQu_res() {
            return qu_res;
        }

        public void setQu_res(QuRes qu_res) {
            this.qu_res = qu_res;
        }

        public List<ActionList> getAction_list() {
            return action_list;
        }

        public void setAction_list(List<ActionList> action_list) {
            this.action_list = action_list;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public static class Schema {
            private int intent_confidence;
            private List<Slots> slots;
            private int domain_confidence;
            private String intent;

            public int getIntent_confidence() {
                return intent_confidence;
            }

            public void setIntent_confidence(int intent_confidence) {
                this.intent_confidence = intent_confidence;
            }

            public List<Slots> getSlots() {
                return slots;
            }

            public void setSlots(List<Slots> slots) {
                this.slots = slots;
            }

            public int getDomain_confidence() {
                return domain_confidence;
            }

            public void setDomain_confidence(int domain_confidence) {
                this.domain_confidence = domain_confidence;
            }

            public String getIntent() {
                return intent;
            }

            public void setIntent(String intent) {
                this.intent = intent;
            }

            public static class Slots {
                private String word_type;
                private List<?> fuzzy_matches;
                private int confidence;
                private int length;
                private String name;
                private String original_word;
                private List<?> sub_slots;
                private int session_offset;
                private int begin;
                private String normalized_word;
                private String merge_method;

                public String getWord_type() {
                    return word_type;
                }

                public void setWord_type(String word_type) {
                    this.word_type = word_type;
                }

                public List<?> getFuzzy_matches() {
                    return fuzzy_matches;
                }

                public void setFuzzy_matches(List<?> fuzzy_matches) {
                    this.fuzzy_matches = fuzzy_matches;
                }

                public int getConfidence() {
                    return confidence;
                }

                public void setConfidence(int confidence) {
                    this.confidence = confidence;
                }

                public int getLength() {
                    return length;
                }

                public void setLength(int length) {
                    this.length = length;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getOriginal_word() {
                    return original_word;
                }

                public void setOriginal_word(String original_word) {
                    this.original_word = original_word;
                }

                public List<?> getSub_slots() {
                    return sub_slots;
                }

                public void setSub_slots(List<?> sub_slots) {
                    this.sub_slots = sub_slots;
                }

                public int getSession_offset() {
                    return session_offset;
                }

                public void setSession_offset(int session_offset) {
                    this.session_offset = session_offset;
                }

                public int getBegin() {
                    return begin;
                }

                public void setBegin(int begin) {
                    this.begin = begin;
                }

                public String getNormalized_word() {
                    return normalized_word;
                }

                public void setNormalized_word(String normalized_word) {
                    this.normalized_word = normalized_word;
                }

                public String getMerge_method() {
                    return merge_method;
                }

                public void setMerge_method(String merge_method) {
                    this.merge_method = merge_method;
                }
            }
        }

        public static class QuRes {
            private List<Candidates> candidates;
            private String qu_res_chosen;
            private SentimentAnalysis sentiment_analysis;
            private List<LexicalAnalysis> lexical_analysis;
            private String raw_query;
            private int status;
            private int timestamp;

            public List<Candidates> getCandidates() {
                return candidates;
            }

            public void setCandidates(List<Candidates> candidates) {
                this.candidates = candidates;
            }

            public String getQu_res_chosen() {
                return qu_res_chosen;
            }

            public void setQu_res_chosen(String qu_res_chosen) {
                this.qu_res_chosen = qu_res_chosen;
            }

            public SentimentAnalysis getSentiment_analysis() {
                return sentiment_analysis;
            }

            public void setSentiment_analysis(SentimentAnalysis sentiment_analysis) {
                this.sentiment_analysis = sentiment_analysis;
            }

            public List<LexicalAnalysis> getLexical_analysis() {
                return lexical_analysis;
            }

            public void setLexical_analysis(List<LexicalAnalysis> lexical_analysis) {
                this.lexical_analysis = lexical_analysis;
            }

            public String getRaw_query() {
                return raw_query;
            }

            public void setRaw_query(String raw_query) {
                this.raw_query = raw_query;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }

            public static class SentimentAnalysis {
                private double pval;
                private String label;

                public double getPval() {
                    return pval;
                }

                public void setPval(double pval) {
                    this.pval = pval;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }
            }

            public static class Candidates {
                private int intent_confidence;
                private String match_info;
                private List<Slots> slots;
                private ExtraInfo extra_info;
                private int confidence;
                private int domain_confidence;
                private String from_who;
                private String intent;
                private boolean intent_need_clarify;

                public int getIntent_confidence() {
                    return intent_confidence;
                }

                public void setIntent_confidence(int intent_confidence) {
                    this.intent_confidence = intent_confidence;
                }

                public String getMatch_info() {
                    return match_info;
                }

                public void setMatch_info(String match_info) {
                    this.match_info = match_info;
                }

                public List<Slots> getSlots() {
                    return slots;
                }

                public void setSlots(List<Slots> slots) {
                    this.slots = slots;
                }

                public ExtraInfo getExtra_info() {
                    return extra_info;
                }

                public void setExtra_info(ExtraInfo extra_info) {
                    this.extra_info = extra_info;
                }

                public int getConfidence() {
                    return confidence;
                }

                public void setConfidence(int confidence) {
                    this.confidence = confidence;
                }

                public int getDomain_confidence() {
                    return domain_confidence;
                }

                public void setDomain_confidence(int domain_confidence) {
                    this.domain_confidence = domain_confidence;
                }

                public String getFrom_who() {
                    return from_who;
                }

                public void setFrom_who(String from_who) {
                    this.from_who = from_who;
                }

                public String getIntent() {
                    return intent;
                }

                public void setIntent(String intent) {
                    this.intent = intent;
                }

                public boolean isIntent_need_clarify() {
                    return intent_need_clarify;
                }

                public void setIntent_need_clarify(boolean intent_need_clarify) {
                    this.intent_need_clarify = intent_need_clarify;
                }

                public static class ExtraInfo {
                }

                public static class Slots {
                    private String word_type;
                    private int father_idx;
                    private List<?> fuzzy_matches;
                    private int confidence;
                    private int length;
                    private String name;
                    private String original_word;
                    private int begin;
                    private boolean need_clarify;
                    private String normalized_word;

                    public String getWord_type() {
                        return word_type;
                    }

                    public void setWord_type(String word_type) {
                        this.word_type = word_type;
                    }

                    public int getFather_idx() {
                        return father_idx;
                    }

                    public void setFather_idx(int father_idx) {
                        this.father_idx = father_idx;
                    }

                    public List<?> getFuzzy_matches() {
                        return fuzzy_matches;
                    }

                    public void setFuzzy_matches(List<?> fuzzy_matches) {
                        this.fuzzy_matches = fuzzy_matches;
                    }

                    public int getConfidence() {
                        return confidence;
                    }

                    public void setConfidence(int confidence) {
                        this.confidence = confidence;
                    }

                    public int getLength() {
                        return length;
                    }

                    public void setLength(int length) {
                        this.length = length;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getOriginal_word() {
                        return original_word;
                    }

                    public void setOriginal_word(String original_word) {
                        this.original_word = original_word;
                    }

                    public int getBegin() {
                        return begin;
                    }

                    public void setBegin(int begin) {
                        this.begin = begin;
                    }

                    public boolean isNeed_clarify() {
                        return need_clarify;
                    }

                    public void setNeed_clarify(boolean need_clarify) {
                        this.need_clarify = need_clarify;
                    }

                    public String getNormalized_word() {
                        return normalized_word;
                    }

                    public void setNormalized_word(String normalized_word) {
                        this.normalized_word = normalized_word;
                    }
                }
            }

            public static class LexicalAnalysis {
                private List<String> etypes;
                private List<String> basic_word;
                private double weight;
                private String term;
                private String type;

                public List<String> getEtypes() {
                    return etypes;
                }

                public void setEtypes(List<String> etypes) {
                    this.etypes = etypes;
                }

                public List<String> getBasic_word() {
                    return basic_word;
                }

                public void setBasic_word(List<String> basic_word) {
                    this.basic_word = basic_word;
                }

                public double getWeight() {
                    return weight;
                }

                public void setWeight(double weight) {
                    this.weight = weight;
                }

                public String getTerm() {
                    return term;
                }

                public void setTerm(String term) {
                    this.term = term;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }

        public static class ActionList {
            private String action_id;
            private RefineDetail refine_detail;
            private int confidence;
            private String custom_reply;
            private String say;
            private String type;

            public String getAction_id() {
                return action_id;
            }

            public void setAction_id(String action_id) {
                this.action_id = action_id;
            }

            public RefineDetail getRefine_detail() {
                return refine_detail;
            }

            public void setRefine_detail(RefineDetail refine_detail) {
                this.refine_detail = refine_detail;
            }

            public int getConfidence() {
                return confidence;
            }

            public void setConfidence(int confidence) {
                this.confidence = confidence;
            }

            public String getCustom_reply() {
                return custom_reply;
            }

            public void setCustom_reply(String custom_reply) {
                this.custom_reply = custom_reply;
            }

            public String getSay() {
                return say;
            }

            public void setSay(String say) {
                this.say = say;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class RefineDetail {
                private List<?> option_list;
                private String interact;
                private String clarify_reason;

                public List<?> getOption_list() {
                    return option_list;
                }

                public void setOption_list(List<?> option_list) {
                    this.option_list = option_list;
                }

                public String getInteract() {
                    return interact;
                }

                public void setInteract(String interact) {
                    this.interact = interact;
                }

                public String getClarify_reason() {
                    return clarify_reason;
                }

                public void setClarify_reason(String clarify_reason) {
                    this.clarify_reason = clarify_reason;
                }
            }
        }
    }
}
