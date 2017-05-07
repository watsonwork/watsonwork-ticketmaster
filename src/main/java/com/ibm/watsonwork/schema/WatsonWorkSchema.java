package com.ibm.watsonwork.schema;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shopify.graphql.support.*;
import com.shopify.graphql.support.Error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WatsonWorkSchema {
    public static QueryRootQuery query(QueryRootQueryDefinition queryDef) {
        StringBuilder queryString = new StringBuilder("{");
        QueryRootQuery query = new QueryRootQuery(queryString);
        queryDef.define(query);
        queryString.append('}');
        return query;
    }

    public static class QueryResponse {
        private TopLevelResponse response;
        private QueryRoot data;

        public QueryResponse(TopLevelResponse response) throws SchemaViolationError {
            this.response = response;
            this.data = response.getData() != null ? new QueryRoot(response.getData()) : null;
        }

        public QueryRoot getData() {
            return data;
        }

        public List<Error> getErrors() {
            return response.getErrors();
        }

        public String toJson() {
            return new Gson().toJson(response);
        }

        public String prettyPrintJson() {
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(response);
        }

        public static QueryResponse fromJson(String json) throws SchemaViolationError {
            final TopLevelResponse response = new Gson().fromJson(json, TopLevelResponse.class);
            return new QueryResponse(response);
        }
    }

    public static MutationRootQuery mutation(MutationRootQueryDefinition queryDef) {
        StringBuilder queryString = new StringBuilder("mutation{");
        MutationRootQuery query = new MutationRootQuery(queryString);
        queryDef.define(query);
        queryString.append('}');
        return query;
    }

    public static class MutationResponse {
        private TopLevelResponse response;
        private MutationRoot data;

        public MutationResponse(TopLevelResponse response) throws SchemaViolationError {
            this.response = response;
            this.data = response.getData() != null ? new MutationRoot(response.getData()) : null;
        }

        public MutationRoot getData() {
            return data;
        }

        public List<Error> getErrors() {
            return response.getErrors();
        }

        public String toJson() {
            return new Gson().toJson(response);
        }

        public String prettyPrintJson() {
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(response);
        }

        public static MutationResponse fromJson(String json) throws SchemaViolationError {
            final TopLevelResponse response = new Gson().fromJson(json, TopLevelResponse.class);
            return new MutationResponse(response);
        }
    }

    public static class ActionSelectInput implements Serializable {
        private String conversationId;

        private String targetDialogId;

        private String targetId;

        private String actionId;

        private String referralMessageId;

        public ActionSelectInput(String conversationId, String targetDialogId, String targetId, String actionId, String referralMessageId) {
            this.conversationId = conversationId;

            this.targetDialogId = targetDialogId;

            this.targetId = targetId;

            this.actionId = actionId;

            this.referralMessageId = referralMessageId;
        }

        public String getConversationId() {
            return conversationId;
        }

        public ActionSelectInput setConversationId(String conversationId) {
            this.conversationId = conversationId;
            return this;
        }

        public String getTargetDialogId() {
            return targetDialogId;
        }

        public ActionSelectInput setTargetDialogId(String targetDialogId) {
            this.targetDialogId = targetDialogId;
            return this;
        }

        public String getTargetId() {
            return targetId;
        }

        public ActionSelectInput setTargetId(String targetId) {
            this.targetId = targetId;
            return this;
        }

        public String getActionId() {
            return actionId;
        }

        public ActionSelectInput setActionId(String actionId) {
            this.actionId = actionId;
            return this;
        }

        public String getReferralMessageId() {
            return referralMessageId;
        }

        public ActionSelectInput setReferralMessageId(String referralMessageId) {
            this.referralMessageId = referralMessageId;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("conversationId:");
            Query.appendQuotedString(_queryBuilder, conversationId);

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("targetDialogId:");
            Query.appendQuotedString(_queryBuilder, targetDialogId);

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("targetId:");
            Query.appendQuotedString(_queryBuilder, targetId);

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("actionId:");
            Query.appendQuotedString(_queryBuilder, actionId);

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("referralMessageId:");
            Query.appendQuotedString(_queryBuilder, referralMessageId);

            _queryBuilder.append('}');
        }
    }

    public interface ActionSelectMutationQueryDefinition {
        void define(ActionSelectMutationQuery _queryBuilder);
    }

    public static class ActionSelectMutationQuery extends Query<ActionSelectMutationQuery> {
        ActionSelectMutationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public ActionSelectMutationQuery successful() {
            startField("successful");

            return this;
        }
    }

    public static class ActionSelectMutation extends AbstractResponse<ActionSelectMutation> {
        public ActionSelectMutation() {
        }

        public ActionSelectMutation(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "successful": {
                        responseData.put(key, jsonAsBoolean(field.getValue(), key));

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {

            return new ArrayList<>();
        }

        public String getGraphQlTypeName() {
            return "ActionSelectMutation";
        }

        public Boolean getSuccessful() {
            return (Boolean) get("successful");
        }

        public ActionSelectMutation setSuccessful(Boolean arg) {
            optimisticData.put("successful", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "successful": return false;

                default: return false;
            }
        }
    }

    public static class ActorInput implements Serializable {
        private String name;

        private String url;

        private String avatar;

        public String getName() {
            return name;
        }

        public ActorInput setName(String name) {
            this.name = name;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public ActorInput setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getAvatar() {
            return avatar;
        }

        public ActorInput setAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            if (name != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("name:");
                Query.appendQuotedString(_queryBuilder, name);
            }

            if (url != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("url:");
                Query.appendQuotedString(_queryBuilder, url);
            }

            if (avatar != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("avatar:");
                Query.appendQuotedString(_queryBuilder, avatar);
            }

            _queryBuilder.append('}');
        }
    }

    public static class AnnotationWrapperInput implements Serializable {
        private GenericAnnotationInput genericAnnotation;

        public AnnotationWrapperInput(GenericAnnotationInput genericAnnotation) {
            this.genericAnnotation = genericAnnotation;
        }

        public GenericAnnotationInput getGenericAnnotation() {
            return genericAnnotation;
        }

        public AnnotationWrapperInput setGenericAnnotation(GenericAnnotationInput genericAnnotation) {
            this.genericAnnotation = genericAnnotation;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("genericAnnotation:");
            genericAnnotation.appendTo(_queryBuilder);

            _queryBuilder.append('}');
        }
    }

    public enum ButtonStyle {
        PRIMARY,

        SECONDARY,

        UNKNOWN_VALUE;

        public static ButtonStyle fromGraphQl(String value) {
            if (value == null) {
                return null;
            }

            switch (value) {
                case "PRIMARY": {
                    return PRIMARY;
                }

                case "SECONDARY": {
                    return SECONDARY;
                }

                default: {
                    return UNKNOWN_VALUE;
                }
            }
        }
        public String toString() {
            switch (this) {
                case PRIMARY: {
                    return "PRIMARY";
                }

                case SECONDARY: {
                    return "SECONDARY";
                }

                default: {
                    return "";
                }
            }
        }
    }

    public static class ButtonWrapperInput implements Serializable {
        private PostbackButtonInput postbackButton;

        public ButtonWrapperInput(PostbackButtonInput postbackButton) {
            this.postbackButton = postbackButton;
        }

        public PostbackButtonInput getPostbackButton() {
            return postbackButton;
        }

        public ButtonWrapperInput setPostbackButton(PostbackButtonInput postbackButton) {
            this.postbackButton = postbackButton;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("postbackButton:");
            postbackButton.appendTo(_queryBuilder);

            _queryBuilder.append('}');
        }
    }

    public interface ConversationQueryDefinition {
        void define(ConversationQuery _queryBuilder);
    }

    public static class ConversationQuery extends Query<ConversationQuery> {
        ConversationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("id");
        }

        public ConversationQuery created() {
            startField("created");

            return this;
        }

        public ConversationQuery updated() {
            startField("updated");

            return this;
        }

        public class MessagesArguments extends Arguments {
            MessagesArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public MessagesArguments oldestTimestamp(String value) {
                if (value != null) {
                    startArgument("oldestTimestamp");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }

            public MessagesArguments mostRecentTimestamp(String value) {
                if (value != null) {
                    startArgument("mostRecentTimestamp");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }

            public MessagesArguments annotationType(String value) {
                if (value != null) {
                    startArgument("annotationType");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }

            public MessagesArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }

            public MessagesArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }

            public MessagesArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public MessagesArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface MessagesArgumentsDefinition {
            void define(MessagesArguments args);
        }

        public ConversationQuery messages(MessageCollectionQueryDefinition queryDef) {
            return messages(args -> {}, queryDef);
        }

        public ConversationQuery messages(MessagesArgumentsDefinition argsDef, MessageCollectionQueryDefinition queryDef) {
            startField("messages");

            MessagesArguments args = new MessagesArguments(_queryBuilder);
            argsDef.define(args);
            MessagesArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new MessageCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public ConversationQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public ConversationQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class Conversation extends AbstractResponse<Conversation> implements Node {
        public Conversation() {
        }

        public Conversation(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "created": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updated": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "messages": {
                        MessageCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new MessageCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public Conversation(ID id) {
            this();
            optimisticData.put("id", id);
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            children.add(this);

            if (getMessages() != null) {
                children.addAll(getMessages().getNodes());
            }

            if (getCreatedBy() != null) {
                children.addAll(getCreatedBy().getNodes());
            }

            if (getUpdatedBy() != null) {
                children.addAll(getUpdatedBy().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "Conversation";
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Conversation setCreated(String arg) {
            optimisticData.put("created", arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Conversation setUpdated(String arg) {
            optimisticData.put("updated", arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        public MessageCollection getMessages() {
            return (MessageCollection) get("messages");
        }

        public Conversation setMessages(MessageCollection arg) {
            optimisticData.put("messages", arg);
            return this;
        }

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Conversation setCreatedBy(Person arg) {
            optimisticData.put("createdBy", arg);
            return this;
        }

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Conversation setUpdatedBy(Person arg) {
            optimisticData.put("updatedBy", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "created": return false;

                case "updated": return false;

                case "id": return false;

                case "messages": return true;

                case "createdBy": return true;

                case "updatedBy": return true;

                default: return false;
            }
        }
    }

    public static class CreateMessageInput implements Serializable {
        private String conversationId;

        private List<AnnotationWrapperInput> annotations;

        public CreateMessageInput(String conversationId) {
            this.conversationId = conversationId;
        }

        public String getConversationId() {
            return conversationId;
        }

        public CreateMessageInput setConversationId(String conversationId) {
            this.conversationId = conversationId;
            return this;
        }

        public List<AnnotationWrapperInput> getAnnotations() {
            return annotations;
        }

        public CreateMessageInput setAnnotations(List<AnnotationWrapperInput> annotations) {
            this.annotations = annotations;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("conversationId:");
            Query.appendQuotedString(_queryBuilder, conversationId);

            if (annotations != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("annotations:");
                _queryBuilder.append('[');

                String listSeperator1 = "";
                for (AnnotationWrapperInput item1 : annotations) {
                    _queryBuilder.append(listSeperator1);
                    listSeperator1 = ",";
                    item1.appendTo(_queryBuilder);
                }
                _queryBuilder.append(']');
            }

            _queryBuilder.append('}');
        }
    }

    public static class CreateSpaceInput implements Serializable {
        private String title;

        private List<String> members;

        public CreateSpaceInput(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public CreateSpaceInput setTitle(String title) {
            this.title = title;
            return this;
        }

        public List<String> getMembers() {
            return members;
        }

        public CreateSpaceInput setMembers(List<String> members) {
            this.members = members;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("title:");
            Query.appendQuotedString(_queryBuilder, title);

            if (members != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("members:");
                _queryBuilder.append('[');

                String listSeperator1 = "";
                for (String item1 : members) {
                    _queryBuilder.append(listSeperator1);
                    listSeperator1 = ",";
                    Query.appendQuotedString(_queryBuilder, item1);
                }
                _queryBuilder.append(']');
            }

            _queryBuilder.append('}');
        }
    }

    public static class CreateTargetedMessageInput implements Serializable {
        private String conversationId;

        private String targetDialogId;

        private String targetUserId;

        private List<AnnotationWrapperInput> annotations;

        public CreateTargetedMessageInput(String conversationId, String targetDialogId, String targetUserId, List<AnnotationWrapperInput> annotations) {
            this.conversationId = conversationId;

            this.targetDialogId = targetDialogId;

            this.targetUserId = targetUserId;

            this.annotations = annotations;
        }

        public String getConversationId() {
            return conversationId;
        }

        public CreateTargetedMessageInput setConversationId(String conversationId) {
            this.conversationId = conversationId;
            return this;
        }

        public String getTargetDialogId() {
            return targetDialogId;
        }

        public CreateTargetedMessageInput setTargetDialogId(String targetDialogId) {
            this.targetDialogId = targetDialogId;
            return this;
        }

        public String getTargetUserId() {
            return targetUserId;
        }

        public CreateTargetedMessageInput setTargetUserId(String targetUserId) {
            this.targetUserId = targetUserId;
            return this;
        }

        public List<AnnotationWrapperInput> getAnnotations() {
            return annotations;
        }

        public CreateTargetedMessageInput setAnnotations(List<AnnotationWrapperInput> annotations) {
            this.annotations = annotations;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("conversationId:");
            Query.appendQuotedString(_queryBuilder, conversationId);

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("targetDialogId:");
            Query.appendQuotedString(_queryBuilder, targetDialogId);

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("targetUserId:");
            Query.appendQuotedString(_queryBuilder, targetUserId);

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("annotations:");
            _queryBuilder.append('[');

            String listSeperator1 = "";
            for (AnnotationWrapperInput item1 : annotations) {
                _queryBuilder.append(listSeperator1);
                listSeperator1 = ",";
                item1.appendTo(_queryBuilder);
            }
            _queryBuilder.append(']');

            _queryBuilder.append('}');
        }
    }

    public interface DeleteMutationQueryDefinition {
        void define(DeleteMutationQuery _queryBuilder);
    }

    public static class DeleteMutationQuery extends Query<DeleteMutationQuery> {
        DeleteMutationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public DeleteMutationQuery successful() {
            startField("successful");

            return this;
        }
    }

    public static class DeleteMutation extends AbstractResponse<DeleteMutation> {
        public DeleteMutation() {
        }

        public DeleteMutation(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "successful": {
                        Boolean optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsBoolean(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {

            return new ArrayList<>();
        }

        public String getGraphQlTypeName() {
            return "DeleteMutation";
        }

        public Boolean getSuccessful() {
            return (Boolean) get("successful");
        }

        public DeleteMutation setSuccessful(Boolean arg) {
            optimisticData.put("successful", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "successful": return false;

                default: return false;
            }
        }
    }

    public static class DeleteSpaceInput implements Serializable {
        private ID id;

        public DeleteSpaceInput(ID id) {
            this.id = id;
        }

        public ID getId() {
            return id;
        }

        public DeleteSpaceInput setId(ID id) {
            this.id = id;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("id:");
            Query.appendQuotedString(_queryBuilder, id.toString());

            _queryBuilder.append('}');
        }
    }

    public interface EntityQueryDefinition {
        void define(EntityQuery _queryBuilder);
    }

    public static class EntityQuery extends Query<EntityQuery> {
        EntityQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public EntityQuery count() {
            startField("count");

            return this;
        }

        public EntityQuery label() {
            startField("label");

            return this;
        }

        public EntityQuery score() {
            startField("score");

            return this;
        }
    }

    public static class Entity extends AbstractResponse<Entity> implements SummaryPhrase {
        public Entity() {
        }

        public Entity(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "count": {
                        Integer optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsInteger(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "label": {
                        responseData.put(key, jsonAsString(field.getValue(), key));

                        break;
                    }

                    case "score": {
                        Double optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsDouble(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {

            return new ArrayList<>();
        }

        public String getGraphQlTypeName() {
            return "Entity";
        }

        public Integer getCount() {
            return (Integer) get("count");
        }

        public Entity setCount(Integer arg) {
            optimisticData.put("count", arg);
            return this;
        }

        public String getLabel() {
            return (String) get("label");
        }

        public Entity setLabel(String arg) {
            optimisticData.put("label", arg);
            return this;
        }

        public Double getScore() {
            return (Double) get("score");
        }

        public Entity setScore(Double arg) {
            optimisticData.put("score", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "count": return false;

                case "label": return false;

                case "score": return false;

                default: return false;
            }
        }
    }

    public static class GenericAnnotationInput implements Serializable {
        private String text;

        private String title;

        private String color;

        private ActorInput actor;

        private List<ButtonWrapperInput> buttons;

        public GenericAnnotationInput(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public GenericAnnotationInput setText(String text) {
            this.text = text;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public GenericAnnotationInput setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getColor() {
            return color;
        }

        public GenericAnnotationInput setColor(String color) {
            this.color = color;
            return this;
        }

        public ActorInput getActor() {
            return actor;
        }

        public GenericAnnotationInput setActor(ActorInput actor) {
            this.actor = actor;
            return this;
        }

        public List<ButtonWrapperInput> getButtons() {
            return buttons;
        }

        public GenericAnnotationInput setButtons(List<ButtonWrapperInput> buttons) {
            this.buttons = buttons;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("text:");
            Query.appendQuotedString(_queryBuilder, text);

            if (title != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("title:");
                Query.appendQuotedString(_queryBuilder, title);
            }

            if (color != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("color:");
                Query.appendQuotedString(_queryBuilder, color);
            }

            if (actor != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("actor:");
                actor.appendTo(_queryBuilder);
            }

            if (buttons != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("buttons:");
                _queryBuilder.append('[');

                String listSeperator1 = "";
                for (ButtonWrapperInput item1 : buttons) {
                    _queryBuilder.append(listSeperator1);
                    listSeperator1 = ",";
                    item1.appendTo(_queryBuilder);
                }
                _queryBuilder.append(']');
            }

            _queryBuilder.append('}');
        }
    }

    public interface KeywordQueryDefinition {
        void define(KeywordQuery _queryBuilder);
    }

    public static class KeywordQuery extends Query<KeywordQuery> {
        KeywordQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public KeywordQuery label() {
            startField("label");

            return this;
        }

        public KeywordQuery score() {
            startField("score");

            return this;
        }
    }

    public static class Keyword extends AbstractResponse<Keyword> implements SummaryPhrase {
        public Keyword() {
        }

        public Keyword(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "label": {
                        responseData.put(key, jsonAsString(field.getValue(), key));

                        break;
                    }

                    case "score": {
                        Double optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsDouble(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {

            return new ArrayList<>();
        }

        public String getGraphQlTypeName() {
            return "Keyword";
        }

        public String getLabel() {
            return (String) get("label");
        }

        public Keyword setLabel(String arg) {
            optimisticData.put("label", arg);
            return this;
        }

        public Double getScore() {
            return (Double) get("score");
        }

        public Keyword setScore(Double arg) {
            optimisticData.put("score", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "label": return false;

                case "score": return false;

                default: return false;
            }
        }
    }

    public enum MemberOperation {
        ADD,

        REMOVE,

        UNKNOWN_VALUE;

        public static MemberOperation fromGraphQl(String value) {
            if (value == null) {
                return null;
            }

            switch (value) {
                case "ADD": {
                    return ADD;
                }

                case "REMOVE": {
                    return REMOVE;
                }

                default: {
                    return UNKNOWN_VALUE;
                }
            }
        }
        public String toString() {
            switch (this) {
                case ADD: {
                    return "ADD";
                }

                case REMOVE: {
                    return "REMOVE";
                }

                default: {
                    return "";
                }
            }
        }
    }

    public interface MessageQueryDefinition {
        void define(MessageQuery _queryBuilder);
    }

    public static class MessageQuery extends Query<MessageQuery> {
        MessageQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("id");
        }

        public MessageQuery content() {
            startField("content");

            return this;
        }

        public MessageQuery contentType() {
            startField("contentType");

            return this;
        }

        public MessageQuery annotations() {
            startField("annotations");

            return this;
        }

        public MessageQuery created() {
            startField("created");

            return this;
        }

        public MessageQuery updated() {
            startField("updated");

            return this;
        }

        public MessageQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MessageQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class Message extends AbstractResponse<Message> implements Node {
        public Message() {
        }

        public Message(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "content": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "contentType": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "annotations": {
                        List<String> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<String> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                String optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = jsonAsString(element1, key);
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "created": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updated": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public Message(ID id) {
            this();
            optimisticData.put("id", id);
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            children.add(this);

            if (getCreatedBy() != null) {
                children.addAll(getCreatedBy().getNodes());
            }

            if (getUpdatedBy() != null) {
                children.addAll(getUpdatedBy().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "Message";
        }

        public String getContent() {
            return (String) get("content");
        }

        public Message setContent(String arg) {
            optimisticData.put("content", arg);
            return this;
        }

        public String getContentType() {
            return (String) get("contentType");
        }

        public Message setContentType(String arg) {
            optimisticData.put("contentType", arg);
            return this;
        }

        public List<String> getAnnotations() {
            return (List<String>) get("annotations");
        }

        public Message setAnnotations(List<String> arg) {
            optimisticData.put("annotations", arg);
            return this;
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Message setCreated(String arg) {
            optimisticData.put("created", arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Message setUpdated(String arg) {
            optimisticData.put("updated", arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Message setCreatedBy(Person arg) {
            optimisticData.put("createdBy", arg);
            return this;
        }

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Message setUpdatedBy(Person arg) {
            optimisticData.put("updatedBy", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "content": return false;

                case "contentType": return false;

                case "annotations": return false;

                case "created": return false;

                case "updated": return false;

                case "id": return false;

                case "createdBy": return true;

                case "updatedBy": return true;

                default: return false;
            }
        }
    }

    public interface MessageCollectionQueryDefinition {
        void define(MessageCollectionQuery _queryBuilder);
    }

    public static class MessageCollectionQuery extends Query<MessageCollectionQuery> {
        MessageCollectionQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public MessageCollectionQuery pageInfo(PageInfoQueryDefinition queryDef) {
            startField("pageInfo");

            _queryBuilder.append('{');
            queryDef.define(new PageInfoQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MessageCollectionQuery items(MessageQueryDefinition queryDef) {
            startField("items");

            _queryBuilder.append('{');
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class MessageCollection extends AbstractResponse<MessageCollection> {
        public MessageCollection() {
        }

        public MessageCollection(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "pageInfo": {
                        responseData.put(key, new PageInfo(jsonAsObject(field.getValue(), key)));

                        break;
                    }

                    case "items": {
                        List<Message> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            Message optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new Message(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        responseData.put(key, list1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getPageInfo() != null) {
                children.addAll(getPageInfo().getNodes());
            }

            if (getItems() != null) {
                for (Message elem: getItems()) {
                    children.addAll(elem.getNodes());
                }
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "MessageCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public MessageCollection setPageInfo(PageInfo arg) {
            optimisticData.put("pageInfo", arg);
            return this;
        }

        public List<Message> getItems() {
            return (List<Message>) get("items");
        }

        public MessageCollection setItems(List<Message> arg) {
            optimisticData.put("items", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "pageInfo": return true;

                case "items": return true;

                default: return false;
            }
        }
    }

    public interface MessageMutationQueryDefinition {
        void define(MessageMutationQuery _queryBuilder);
    }

    public static class MessageMutationQuery extends Query<MessageMutationQuery> {
        MessageMutationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public MessageMutationQuery message(MessageQueryDefinition queryDef) {
            startField("message");

            _queryBuilder.append('{');
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class MessageMutation extends AbstractResponse<MessageMutation> {
        public MessageMutation() {
        }

        public MessageMutation(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "message": {
                        Message optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Message(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getMessage() != null) {
                children.addAll(getMessage().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "MessageMutation";
        }

        public Message getMessage() {
            return (Message) get("message");
        }

        public MessageMutation setMessage(Message arg) {
            optimisticData.put("message", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "message": return true;

                default: return false;
            }
        }
    }

    public interface MutationRootQueryDefinition {
        void define(MutationRootQuery _queryBuilder);
    }

    public static class MutationRootQuery extends Query<MutationRootQuery> {
        MutationRootQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public MutationRootQuery createSpace(CreateSpaceInput input, SpaceMutationQueryDefinition queryDef) {
            startField("createSpace");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new SpaceMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MutationRootQuery updateSpace(UpdateSpaceInput input, SpaceMutationQueryDefinition queryDef) {
            startField("updateSpace");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new SpaceMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MutationRootQuery deleteSpace(DeleteSpaceInput input, DeleteMutationQueryDefinition queryDef) {
            startField("deleteSpace");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new DeleteMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MutationRootQuery createMessage(CreateMessageInput input, MessageMutationQueryDefinition queryDef) {
            startField("createMessage");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new MessageMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MutationRootQuery selectAction(ActionSelectInput input, ActionSelectMutationQueryDefinition queryDef) {
            startField("selectAction");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new ActionSelectMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public MutationRootQuery createTargetedMessage(CreateTargetedMessageInput input, TargetedMessageMutationQueryDefinition queryDef) {
            startField("createTargetedMessage");

            _queryBuilder.append("(input:");
            input.appendTo(_queryBuilder);

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new TargetedMessageMutationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public String toString() {
            return _queryBuilder.toString();
        }
    }

    public static class MutationRoot extends AbstractResponse<MutationRoot> {
        public MutationRoot() {
        }

        public MutationRoot(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "createSpace": {
                        SpaceMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new SpaceMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updateSpace": {
                        SpaceMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new SpaceMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "deleteSpace": {
                        DeleteMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new DeleteMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "createMessage": {
                        MessageMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new MessageMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "selectAction": {
                        ActionSelectMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new ActionSelectMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "createTargetedMessage": {
                        TargetedMessageMutation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new TargetedMessageMutation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getCreateSpace() != null) {
                children.addAll(getCreateSpace().getNodes());
            }

            if (getUpdateSpace() != null) {
                children.addAll(getUpdateSpace().getNodes());
            }

            if (getDeleteSpace() != null) {
                children.addAll(getDeleteSpace().getNodes());
            }

            if (getCreateMessage() != null) {
                children.addAll(getCreateMessage().getNodes());
            }

            if (getSelectAction() != null) {
                children.addAll(getSelectAction().getNodes());
            }

            if (getCreateTargetedMessage() != null) {
                children.addAll(getCreateTargetedMessage().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "MutationRoot";
        }

        public SpaceMutation getCreateSpace() {
            return (SpaceMutation) get("createSpace");
        }

        public MutationRoot setCreateSpace(SpaceMutation arg) {
            optimisticData.put("createSpace", arg);
            return this;
        }

        public SpaceMutation getUpdateSpace() {
            return (SpaceMutation) get("updateSpace");
        }

        public MutationRoot setUpdateSpace(SpaceMutation arg) {
            optimisticData.put("updateSpace", arg);
            return this;
        }

        public DeleteMutation getDeleteSpace() {
            return (DeleteMutation) get("deleteSpace");
        }

        public MutationRoot setDeleteSpace(DeleteMutation arg) {
            optimisticData.put("deleteSpace", arg);
            return this;
        }

        public MessageMutation getCreateMessage() {
            return (MessageMutation) get("createMessage");
        }

        public MutationRoot setCreateMessage(MessageMutation arg) {
            optimisticData.put("createMessage", arg);
            return this;
        }

        public ActionSelectMutation getSelectAction() {
            return (ActionSelectMutation) get("selectAction");
        }

        public MutationRoot setSelectAction(ActionSelectMutation arg) {
            optimisticData.put("selectAction", arg);
            return this;
        }

        public TargetedMessageMutation getCreateTargetedMessage() {
            return (TargetedMessageMutation) get("createTargetedMessage");
        }

        public MutationRoot setCreateTargetedMessage(TargetedMessageMutation arg) {
            optimisticData.put("createTargetedMessage", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "createSpace": return true;

                case "updateSpace": return true;

                case "deleteSpace": return true;

                case "createMessage": return true;

                case "selectAction": return true;

                case "createTargetedMessage": return true;

                default: return false;
            }
        }
    }

    public interface NodeQueryDefinition {
        void define(NodeQuery _queryBuilder);
    }

    public static class NodeQuery extends Query<NodeQuery> {
        NodeQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("__typename");
        }

        public NodeQuery id() {
            startField("id");

            return this;
        }

        public NodeQuery onConversation(ConversationQueryDefinition queryDef) {
            startInlineFragment("Conversation");
            queryDef.define(new ConversationQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }

        public NodeQuery onMessage(MessageQueryDefinition queryDef) {
            startInlineFragment("Message");
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }

        public NodeQuery onPerson(PersonQueryDefinition queryDef) {
            startInlineFragment("Person");
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }

        public NodeQuery onSpace(SpaceQueryDefinition queryDef) {
            startInlineFragment("Space");
            queryDef.define(new SpaceQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }
    }

    public interface Node {
        String getGraphQlTypeName();

        ID getId();
    }

    public static class UnknownNode extends AbstractResponse<UnknownNode> implements Node {
        public UnknownNode() {
        }

        public UnknownNode(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {

            return new ArrayList<>();
        }

        public static Node create(JsonObject fields) throws SchemaViolationError {
            String typeName = fields.getAsJsonPrimitive("__typename").getAsString();
            switch (typeName) {
                case "Conversation": {
                    return new Conversation(fields);
                }

                case "Message": {
                    return new Message(fields);
                }

                case "Person": {
                    return new Person(fields);
                }

                case "Space": {
                    return new Space(fields);
                }

                default: {
                    return new UnknownNode(fields);
                }
            }
        }

        public String getGraphQlTypeName() {
            return (String) get("__typename");
        }

        public ID getId() {
            return (ID) get("id");
        }

        public UnknownNode setId(ID arg) {
            optimisticData.put("id", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "id": return false;

                default: return false;
            }
        }
    }

    public interface PageInfoQueryDefinition {
        void define(PageInfoQuery _queryBuilder);
    }

    public static class PageInfoQuery extends Query<PageInfoQuery> {
        PageInfoQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public PageInfoQuery startCursor() {
            startField("startCursor");

            return this;
        }

        public PageInfoQuery endCursor() {
            startField("endCursor");

            return this;
        }

        public PageInfoQuery hasPreviousPage() {
            startField("hasPreviousPage");

            return this;
        }

        public PageInfoQuery hasNextPage() {
            startField("hasNextPage");

            return this;
        }
    }

    public static class PageInfo extends AbstractResponse<PageInfo> {
        public PageInfo() {
        }

        public PageInfo(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "startCursor": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "endCursor": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "hasPreviousPage": {
                        responseData.put(key, jsonAsBoolean(field.getValue(), key));

                        break;
                    }

                    case "hasNextPage": {
                        responseData.put(key, jsonAsBoolean(field.getValue(), key));

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {

            return new ArrayList<>();
        }

        public String getGraphQlTypeName() {
            return "PageInfo";
        }

        public String getStartCursor() {
            return (String) get("startCursor");
        }

        public PageInfo setStartCursor(String arg) {
            optimisticData.put("startCursor", arg);
            return this;
        }

        public String getEndCursor() {
            return (String) get("endCursor");
        }

        public PageInfo setEndCursor(String arg) {
            optimisticData.put("endCursor", arg);
            return this;
        }

        public Boolean getHasPreviousPage() {
            return (Boolean) get("hasPreviousPage");
        }

        public PageInfo setHasPreviousPage(Boolean arg) {
            optimisticData.put("hasPreviousPage", arg);
            return this;
        }

        public Boolean getHasNextPage() {
            return (Boolean) get("hasNextPage");
        }

        public PageInfo setHasNextPage(Boolean arg) {
            optimisticData.put("hasNextPage", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "startCursor": return false;

                case "endCursor": return false;

                case "hasPreviousPage": return false;

                case "hasNextPage": return false;

                default: return false;
            }
        }
    }

    public interface PersonQueryDefinition {
        void define(PersonQuery _queryBuilder);
    }

    public static class PersonQuery extends Query<PersonQuery> {
        PersonQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("id");
        }

        public PersonQuery displayName() {
            startField("displayName");

            return this;
        }

        public PersonQuery extId() {
            startField("extId");

            return this;
        }

        public PersonQuery email() {
            startField("email");

            return this;
        }

        public PersonQuery emailAddresses() {
            startField("emailAddresses");

            return this;
        }

        public PersonQuery photoUrl() {
            startField("photoUrl");

            return this;
        }

        public PersonQuery customerId() {
            startField("customerId");

            return this;
        }

        public PersonQuery created() {
            startField("created");

            return this;
        }

        public PersonQuery updated() {
            startField("updated");

            return this;
        }

        public PersonQuery presence() {
            startField("presence");

            return this;
        }

        public PersonQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public PersonQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class Person extends AbstractResponse<Person> implements Node {
        public Person() {
        }

        public Person(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "displayName": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "extId": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "email": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "emailAddresses": {
                        List<String> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<String> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                String optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = jsonAsString(element1, key);
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "photoUrl": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "customerId": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "created": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updated": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "presence": {
                        PresenceStatus optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = PresenceStatus.fromGraphQl(jsonAsString(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public Person(ID id) {
            this();
            optimisticData.put("id", id);
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            children.add(this);

            if (getCreatedBy() != null) {
                children.addAll(getCreatedBy().getNodes());
            }

            if (getUpdatedBy() != null) {
                children.addAll(getUpdatedBy().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "Person";
        }

        public String getDisplayName() {
            return (String) get("displayName");
        }

        public Person setDisplayName(String arg) {
            optimisticData.put("displayName", arg);
            return this;
        }

        public String getExtId() {
            return (String) get("extId");
        }

        public Person setExtId(String arg) {
            optimisticData.put("extId", arg);
            return this;
        }

        public String getEmail() {
            return (String) get("email");
        }

        public Person setEmail(String arg) {
            optimisticData.put("email", arg);
            return this;
        }

        public List<String> getEmailAddresses() {
            return (List<String>) get("emailAddresses");
        }

        public Person setEmailAddresses(List<String> arg) {
            optimisticData.put("emailAddresses", arg);
            return this;
        }

        public String getPhotoUrl() {
            return (String) get("photoUrl");
        }

        public Person setPhotoUrl(String arg) {
            optimisticData.put("photoUrl", arg);
            return this;
        }

        public String getCustomerId() {
            return (String) get("customerId");
        }

        public Person setCustomerId(String arg) {
            optimisticData.put("customerId", arg);
            return this;
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Person setCreated(String arg) {
            optimisticData.put("created", arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Person setUpdated(String arg) {
            optimisticData.put("updated", arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        public PresenceStatus getPresence() {
            return (PresenceStatus) get("presence");
        }

        public Person setPresence(PresenceStatus arg) {
            optimisticData.put("presence", arg);
            return this;
        }

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Person setCreatedBy(Person arg) {
            optimisticData.put("createdBy", arg);
            return this;
        }

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Person setUpdatedBy(Person arg) {
            optimisticData.put("updatedBy", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "displayName": return false;

                case "extId": return false;

                case "email": return false;

                case "emailAddresses": return false;

                case "photoUrl": return false;

                case "customerId": return false;

                case "created": return false;

                case "updated": return false;

                case "id": return false;

                case "presence": return false;

                case "createdBy": return true;

                case "updatedBy": return true;

                default: return false;
            }
        }
    }

    public interface PersonCollectionQueryDefinition {
        void define(PersonCollectionQuery _queryBuilder);
    }

    public static class PersonCollectionQuery extends Query<PersonCollectionQuery> {
        PersonCollectionQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public PersonCollectionQuery pageInfo(PageInfoQueryDefinition queryDef) {
            startField("pageInfo");

            _queryBuilder.append('{');
            queryDef.define(new PageInfoQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public PersonCollectionQuery items(PersonQueryDefinition queryDef) {
            startField("items");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class PersonCollection extends AbstractResponse<PersonCollection> {
        public PersonCollection() {
        }

        public PersonCollection(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "pageInfo": {
                        responseData.put(key, new PageInfo(jsonAsObject(field.getValue(), key)));

                        break;
                    }

                    case "items": {
                        List<Person> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            Person optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new Person(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        responseData.put(key, list1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getPageInfo() != null) {
                children.addAll(getPageInfo().getNodes());
            }

            if (getItems() != null) {
                for (Person elem: getItems()) {
                    children.addAll(elem.getNodes());
                }
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "PersonCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public PersonCollection setPageInfo(PageInfo arg) {
            optimisticData.put("pageInfo", arg);
            return this;
        }

        public List<Person> getItems() {
            return (List<Person>) get("items");
        }

        public PersonCollection setItems(List<Person> arg) {
            optimisticData.put("items", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "pageInfo": return true;

                case "items": return true;

                default: return false;
            }
        }
    }

    public static class PostbackButtonInput implements Serializable {
        private String id;

        private String title;

        private ButtonStyle style;

        public PostbackButtonInput(String id, String title, ButtonStyle style) {
            this.id = id;

            this.title = title;

            this.style = style;
        }

        public String getId() {
            return id;
        }

        public PostbackButtonInput setId(String id) {
            this.id = id;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public PostbackButtonInput setTitle(String title) {
            this.title = title;
            return this;
        }

        public ButtonStyle getStyle() {
            return style;
        }

        public PostbackButtonInput setStyle(ButtonStyle style) {
            this.style = style;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("id:");
            Query.appendQuotedString(_queryBuilder, id);

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("title:");
            Query.appendQuotedString(_queryBuilder, title);

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("style:");
            _queryBuilder.append(style.toString());

            _queryBuilder.append('}');
        }
    }

    public enum PresenceStatus {
        OFFLINE,

        ONLINE,

        UNKNOWN_VALUE;

        public static PresenceStatus fromGraphQl(String value) {
            if (value == null) {
                return null;
            }

            switch (value) {
                case "offline": {
                    return OFFLINE;
                }

                case "online": {
                    return ONLINE;
                }

                default: {
                    return UNKNOWN_VALUE;
                }
            }
        }
        public String toString() {
            switch (this) {
                case OFFLINE: {
                    return "offline";
                }

                case ONLINE: {
                    return "online";
                }

                default: {
                    return "";
                }
            }
        }
    }

    public interface QueryRootQueryDefinition {
        void define(QueryRootQuery _queryBuilder);
    }

    public static class QueryRootQuery extends Query<QueryRootQuery> {
        QueryRootQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public QueryRootQuery me(PersonQueryDefinition queryDef) {
            startField("me");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class PeopleArguments extends Arguments {
            PeopleArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public PeopleArguments name(String value) {
                if (value != null) {
                    startArgument("name");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }

            public PeopleArguments id(List<String> value) {
                if (value != null) {
                    startArgument("id");
                    _queryBuilder.append('[');

                    String listSeperator1 = "";
                    for (String item1 : value) {
                        _queryBuilder.append(listSeperator1);
                        listSeperator1 = ",";
                        Query.appendQuotedString(_queryBuilder, item1);
                    }
                    _queryBuilder.append(']');
                }
                return this;
            }

            public PeopleArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }

            public PeopleArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }

            public PeopleArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public PeopleArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface PeopleArgumentsDefinition {
            void define(PeopleArguments args);
        }

        public QueryRootQuery people(PersonCollectionQueryDefinition queryDef) {
            return people(args -> {}, queryDef);
        }

        public QueryRootQuery people(PeopleArgumentsDefinition argsDef, PersonCollectionQueryDefinition queryDef) {
            startField("people");

            PeopleArguments args = new PeopleArguments(_queryBuilder);
            argsDef.define(args);
            PeopleArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new PersonCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class SpacesArguments extends Arguments {
            SpacesArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public SpacesArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }

            public SpacesArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }

            public SpacesArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public SpacesArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface SpacesArgumentsDefinition {
            void define(SpacesArguments args);
        }

        public QueryRootQuery spaces(SpaceCollectionQueryDefinition queryDef) {
            return spaces(args -> {}, queryDef);
        }

        public QueryRootQuery spaces(SpacesArgumentsDefinition argsDef, SpaceCollectionQueryDefinition queryDef) {
            startField("spaces");

            SpacesArguments args = new SpacesArguments(_queryBuilder);
            argsDef.define(args);
            SpacesArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new SpaceCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public class PersonArguments extends Arguments {
            PersonArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public PersonArguments id(ID value) {
                if (value != null) {
                    startArgument("id");
                    Query.appendQuotedString(_queryBuilder, value.toString());
                }
                return this;
            }

            public PersonArguments email(String value) {
                if (value != null) {
                    startArgument("email");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }
        }

        public interface PersonArgumentsDefinition {
            void define(PersonArguments args);
        }

        public QueryRootQuery person(PersonQueryDefinition queryDef) {
            return person(args -> {}, queryDef);
        }

        public QueryRootQuery person(PersonArgumentsDefinition argsDef, PersonQueryDefinition queryDef) {
            startField("person");

            PersonArguments args = new PersonArguments(_queryBuilder);
            argsDef.define(args);
            PersonArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public QueryRootQuery space(ID id, SpaceQueryDefinition queryDef) {
            startField("space");

            _queryBuilder.append("(id:");
            Query.appendQuotedString(_queryBuilder, id.toString());

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new SpaceQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public QueryRootQuery conversation(ID id, ConversationQueryDefinition queryDef) {
            startField("conversation");

            _queryBuilder.append("(id:");
            Query.appendQuotedString(_queryBuilder, id.toString());

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new ConversationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public QueryRootQuery message(ID id, MessageQueryDefinition queryDef) {
            startField("message");

            _queryBuilder.append("(id:");
            Query.appendQuotedString(_queryBuilder, id.toString());

            _queryBuilder.append(')');

            _queryBuilder.append('{');
            queryDef.define(new MessageQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public String toString() {
            return _queryBuilder.toString();
        }
    }

    public static class QueryRoot extends AbstractResponse<QueryRoot> {
        public QueryRoot() {
        }

        public QueryRoot(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "me": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "people": {
                        PersonCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new PersonCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "spaces": {
                        SpaceCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new SpaceCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "person": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "space": {
                        Space optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Space(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "conversation": {
                        Conversation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Conversation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "message": {
                        Message optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Message(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getMe() != null) {
                children.addAll(getMe().getNodes());
            }

            if (getPeople() != null) {
                children.addAll(getPeople().getNodes());
            }

            if (getSpaces() != null) {
                children.addAll(getSpaces().getNodes());
            }

            if (getPerson() != null) {
                children.addAll(getPerson().getNodes());
            }

            if (getSpace() != null) {
                children.addAll(getSpace().getNodes());
            }

            if (getConversation() != null) {
                children.addAll(getConversation().getNodes());
            }

            if (getMessage() != null) {
                children.addAll(getMessage().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "QueryRoot";
        }

        public Person getMe() {
            return (Person) get("me");
        }

        public QueryRoot setMe(Person arg) {
            optimisticData.put("me", arg);
            return this;
        }

        public PersonCollection getPeople() {
            return (PersonCollection) get("people");
        }

        public QueryRoot setPeople(PersonCollection arg) {
            optimisticData.put("people", arg);
            return this;
        }

        public SpaceCollection getSpaces() {
            return (SpaceCollection) get("spaces");
        }

        public QueryRoot setSpaces(SpaceCollection arg) {
            optimisticData.put("spaces", arg);
            return this;
        }

        public Person getPerson() {
            return (Person) get("person");
        }

        public QueryRoot setPerson(Person arg) {
            optimisticData.put("person", arg);
            return this;
        }

        public Space getSpace() {
            return (Space) get("space");
        }

        public QueryRoot setSpace(Space arg) {
            optimisticData.put("space", arg);
            return this;
        }

        public Conversation getConversation() {
            return (Conversation) get("conversation");
        }

        public QueryRoot setConversation(Conversation arg) {
            optimisticData.put("conversation", arg);
            return this;
        }

        public Message getMessage() {
            return (Message) get("message");
        }

        public QueryRoot setMessage(Message arg) {
            optimisticData.put("message", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "me": return true;

                case "people": return true;

                case "spaces": return true;

                case "person": return true;

                case "space": return true;

                case "conversation": return true;

                case "message": return true;

                default: return false;
            }
        }
    }

    public interface SpaceQueryDefinition {
        void define(SpaceQuery _queryBuilder);
    }

    public static class SpaceQuery extends Query<SpaceQuery> {
        SpaceQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("id");
        }

        public SpaceQuery title() {
            startField("title");

            return this;
        }

        public SpaceQuery description() {
            startField("description");

            return this;
        }

        public SpaceQuery created() {
            startField("created");

            return this;
        }

        public SpaceQuery updated() {
            startField("updated");

            return this;
        }

        public class MembersArguments extends Arguments {
            MembersArguments(StringBuilder _queryBuilder) {
                super(_queryBuilder, true);
            }

            public MembersArguments before(String value) {
                if (value != null) {
                    startArgument("before");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }

            public MembersArguments after(String value) {
                if (value != null) {
                    startArgument("after");
                    Query.appendQuotedString(_queryBuilder, value);
                }
                return this;
            }

            public MembersArguments first(Integer value) {
                if (value != null) {
                    startArgument("first");
                    _queryBuilder.append(value);
                }
                return this;
            }

            public MembersArguments last(Integer value) {
                if (value != null) {
                    startArgument("last");
                    _queryBuilder.append(value);
                }
                return this;
            }
        }

        public interface MembersArgumentsDefinition {
            void define(MembersArguments args);
        }

        public SpaceQuery members(PersonCollectionQueryDefinition queryDef) {
            return members(args -> {}, queryDef);
        }

        public SpaceQuery members(MembersArgumentsDefinition argsDef, PersonCollectionQueryDefinition queryDef) {
            startField("members");

            MembersArguments args = new MembersArguments(_queryBuilder);
            argsDef.define(args);
            MembersArguments.end(args);

            _queryBuilder.append('{');
            queryDef.define(new PersonCollectionQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SpaceQuery membersUpdated() {
            startField("membersUpdated");

            return this;
        }

        public SpaceQuery conversation(ConversationQueryDefinition queryDef) {
            startField("conversation");

            _queryBuilder.append('{');
            queryDef.define(new ConversationQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SpaceQuery createdBy(PersonQueryDefinition queryDef) {
            startField("createdBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SpaceQuery updatedBy(PersonQueryDefinition queryDef) {
            startField("updatedBy");

            _queryBuilder.append('{');
            queryDef.define(new PersonQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class Space extends AbstractResponse<Space> implements Node {
        public Space() {
        }

        public Space(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "title": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "description": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "created": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updated": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        responseData.put(key, new ID(jsonAsString(field.getValue(), key)));

                        break;
                    }

                    case "members": {
                        PersonCollection optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new PersonCollection(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "membersUpdated": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "conversation": {
                        Conversation optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Conversation(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "createdBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "updatedBy": {
                        Person optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Person(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public Space(ID id) {
            this();
            optimisticData.put("id", id);
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            children.add(this);

            if (getMembers() != null) {
                children.addAll(getMembers().getNodes());
            }

            if (getConversation() != null) {
                children.addAll(getConversation().getNodes());
            }

            if (getCreatedBy() != null) {
                children.addAll(getCreatedBy().getNodes());
            }

            if (getUpdatedBy() != null) {
                children.addAll(getUpdatedBy().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "Space";
        }

        public String getTitle() {
            return (String) get("title");
        }

        public Space setTitle(String arg) {
            optimisticData.put("title", arg);
            return this;
        }

        public String getDescription() {
            return (String) get("description");
        }

        public Space setDescription(String arg) {
            optimisticData.put("description", arg);
            return this;
        }

        public String getCreated() {
            return (String) get("created");
        }

        public Space setCreated(String arg) {
            optimisticData.put("created", arg);
            return this;
        }

        public String getUpdated() {
            return (String) get("updated");
        }

        public Space setUpdated(String arg) {
            optimisticData.put("updated", arg);
            return this;
        }

        public ID getId() {
            return (ID) get("id");
        }

        public PersonCollection getMembers() {
            return (PersonCollection) get("members");
        }

        public Space setMembers(PersonCollection arg) {
            optimisticData.put("members", arg);
            return this;
        }

        public String getMembersUpdated() {
            return (String) get("membersUpdated");
        }

        public Space setMembersUpdated(String arg) {
            optimisticData.put("membersUpdated", arg);
            return this;
        }

        public Conversation getConversation() {
            return (Conversation) get("conversation");
        }

        public Space setConversation(Conversation arg) {
            optimisticData.put("conversation", arg);
            return this;
        }

        public Person getCreatedBy() {
            return (Person) get("createdBy");
        }

        public Space setCreatedBy(Person arg) {
            optimisticData.put("createdBy", arg);
            return this;
        }

        public Person getUpdatedBy() {
            return (Person) get("updatedBy");
        }

        public Space setUpdatedBy(Person arg) {
            optimisticData.put("updatedBy", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "title": return false;

                case "description": return false;

                case "created": return false;

                case "updated": return false;

                case "id": return false;

                case "members": return true;

                case "membersUpdated": return false;

                case "conversation": return true;

                case "createdBy": return true;

                case "updatedBy": return true;

                default: return false;
            }
        }
    }

    public interface SpaceCollectionQueryDefinition {
        void define(SpaceCollectionQuery _queryBuilder);
    }

    public static class SpaceCollectionQuery extends Query<SpaceCollectionQuery> {
        SpaceCollectionQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public SpaceCollectionQuery pageInfo(PageInfoQueryDefinition queryDef) {
            startField("pageInfo");

            _queryBuilder.append('{');
            queryDef.define(new PageInfoQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }

        public SpaceCollectionQuery items(SpaceQueryDefinition queryDef) {
            startField("items");

            _queryBuilder.append('{');
            queryDef.define(new SpaceQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class SpaceCollection extends AbstractResponse<SpaceCollection> {
        public SpaceCollection() {
        }

        public SpaceCollection(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "pageInfo": {
                        responseData.put(key, new PageInfo(jsonAsObject(field.getValue(), key)));

                        break;
                    }

                    case "items": {
                        List<Space> list1 = new ArrayList<>();
                        for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                            Space optional2 = null;
                            if (!element1.isJsonNull()) {
                                optional2 = new Space(jsonAsObject(element1, key));
                            }

                            list1.add(optional2);
                        }

                        responseData.put(key, list1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getPageInfo() != null) {
                children.addAll(getPageInfo().getNodes());
            }

            if (getItems() != null) {
                for (Space elem: getItems()) {
                    children.addAll(elem.getNodes());
                }
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "SpaceCollection";
        }

        public PageInfo getPageInfo() {
            return (PageInfo) get("pageInfo");
        }

        public SpaceCollection setPageInfo(PageInfo arg) {
            optimisticData.put("pageInfo", arg);
            return this;
        }

        public List<Space> getItems() {
            return (List<Space>) get("items");
        }

        public SpaceCollection setItems(List<Space> arg) {
            optimisticData.put("items", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "pageInfo": return true;

                case "items": return true;

                default: return false;
            }
        }
    }

    public interface SpaceMutationQueryDefinition {
        void define(SpaceMutationQuery _queryBuilder);
    }

    public static class SpaceMutationQuery extends Query<SpaceMutationQuery> {
        SpaceMutationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public SpaceMutationQuery memberIdsChanged() {
            startField("memberIdsChanged");

            return this;
        }

        public SpaceMutationQuery space(SpaceQueryDefinition queryDef) {
            startField("space");

            _queryBuilder.append('{');
            queryDef.define(new SpaceQuery(_queryBuilder));
            _queryBuilder.append('}');

            return this;
        }
    }

    public static class SpaceMutation extends AbstractResponse<SpaceMutation> {
        public SpaceMutation() {
        }

        public SpaceMutation(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "memberIdsChanged": {
                        List<String> optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            List<String> list1 = new ArrayList<>();
                            for (JsonElement element1 : jsonAsArray(field.getValue(), key)) {
                                String optional2 = null;
                                if (!element1.isJsonNull()) {
                                    optional2 = jsonAsString(element1, key);
                                }

                                list1.add(optional2);
                            }

                            optional1 = list1;
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "space": {
                        Space optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new Space(jsonAsObject(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {
            List<Node> children = new ArrayList<>();

            if (getSpace() != null) {
                children.addAll(getSpace().getNodes());
            }

            return children;
        }

        public String getGraphQlTypeName() {
            return "SpaceMutation";
        }

        public List<String> getMemberIdsChanged() {
            return (List<String>) get("memberIdsChanged");
        }

        public SpaceMutation setMemberIdsChanged(List<String> arg) {
            optimisticData.put("memberIdsChanged", arg);
            return this;
        }

        public Space getSpace() {
            return (Space) get("space");
        }

        public SpaceMutation setSpace(Space arg) {
            optimisticData.put("space", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "memberIdsChanged": return false;

                case "space": return true;

                default: return false;
            }
        }
    }

    public interface SummaryPhraseQueryDefinition {
        void define(SummaryPhraseQuery _queryBuilder);
    }

    public static class SummaryPhraseQuery extends Query<SummaryPhraseQuery> {
        SummaryPhraseQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);

            startField("__typename");
        }

        public SummaryPhraseQuery label() {
            startField("label");

            return this;
        }

        public SummaryPhraseQuery score() {
            startField("score");

            return this;
        }

        public SummaryPhraseQuery onEntity(EntityQueryDefinition queryDef) {
            startInlineFragment("Entity");
            queryDef.define(new EntityQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }

        public SummaryPhraseQuery onKeyword(KeywordQueryDefinition queryDef) {
            startInlineFragment("Keyword");
            queryDef.define(new KeywordQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }

        public SummaryPhraseQuery onTaxonomy(TaxonomyQueryDefinition queryDef) {
            startInlineFragment("Taxonomy");
            queryDef.define(new TaxonomyQuery(_queryBuilder));
            _queryBuilder.append('}');
            return this;
        }
    }

    public interface SummaryPhrase {
        String getGraphQlTypeName();

        String getLabel();

        Double getScore();
    }

    public static class UnknownSummaryPhrase extends AbstractResponse<UnknownSummaryPhrase> implements SummaryPhrase {
        public UnknownSummaryPhrase() {
        }

        public UnknownSummaryPhrase(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "label": {
                        responseData.put(key, jsonAsString(field.getValue(), key));

                        break;
                    }

                    case "score": {
                        Double optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsDouble(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {

            return new ArrayList<>();
        }

        public static SummaryPhrase create(JsonObject fields) throws SchemaViolationError {
            String typeName = fields.getAsJsonPrimitive("__typename").getAsString();
            switch (typeName) {
                case "Entity": {
                    return new Entity(fields);
                }

                case "Keyword": {
                    return new Keyword(fields);
                }

                case "Taxonomy": {
                    return new Taxonomy(fields);
                }

                default: {
                    return new UnknownSummaryPhrase(fields);
                }
            }
        }

        public String getGraphQlTypeName() {
            return (String) get("__typename");
        }

        public String getLabel() {
            return (String) get("label");
        }

        public UnknownSummaryPhrase setLabel(String arg) {
            optimisticData.put("label", arg);
            return this;
        }

        public Double getScore() {
            return (Double) get("score");
        }

        public UnknownSummaryPhrase setScore(Double arg) {
            optimisticData.put("score", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "label": return false;

                case "score": return false;

                default: return false;
            }
        }
    }

    public interface TargetedMessageMutationQueryDefinition {
        void define(TargetedMessageMutationQuery _queryBuilder);
    }

    public static class TargetedMessageMutationQuery extends Query<TargetedMessageMutationQuery> {
        TargetedMessageMutationQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public TargetedMessageMutationQuery successful() {
            startField("successful");

            return this;
        }
    }

    public static class TargetedMessageMutation extends AbstractResponse<TargetedMessageMutation> {
        public TargetedMessageMutation() {
        }

        public TargetedMessageMutation(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "successful": {
                        responseData.put(key, jsonAsBoolean(field.getValue(), key));

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {

            return new ArrayList<>();
        }

        public String getGraphQlTypeName() {
            return "TargetedMessageMutation";
        }

        public Boolean getSuccessful() {
            return (Boolean) get("successful");
        }

        public TargetedMessageMutation setSuccessful(Boolean arg) {
            optimisticData.put("successful", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "successful": return false;

                default: return false;
            }
        }
    }

    public interface TaxonomyQueryDefinition {
        void define(TaxonomyQuery _queryBuilder);
    }

    public static class TaxonomyQuery extends Query<TaxonomyQuery> {
        TaxonomyQuery(StringBuilder _queryBuilder) {
            super(_queryBuilder);
        }

        public TaxonomyQuery category() {
            startField("category");

            return this;
        }

        public TaxonomyQuery label() {
            startField("label");

            return this;
        }

        public TaxonomyQuery score() {
            startField("score");

            return this;
        }
    }

    public static class Taxonomy extends AbstractResponse<Taxonomy> implements SummaryPhrase {
        public Taxonomy() {
        }

        public Taxonomy(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "category": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "label": {
                        responseData.put(key, jsonAsString(field.getValue(), key));

                        break;
                    }

                    case "score": {
                        Double optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsDouble(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public List<Node> getNodes() {

            return new ArrayList<>();
        }

        public String getGraphQlTypeName() {
            return "Taxonomy";
        }

        public String getCategory() {
            return (String) get("category");
        }

        public Taxonomy setCategory(String arg) {
            optimisticData.put("category", arg);
            return this;
        }

        public String getLabel() {
            return (String) get("label");
        }

        public Taxonomy setLabel(String arg) {
            optimisticData.put("label", arg);
            return this;
        }

        public Double getScore() {
            return (Double) get("score");
        }

        public Taxonomy setScore(Double arg) {
            optimisticData.put("score", arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (key) {
                case "category": return false;

                case "label": return false;

                case "score": return false;

                default: return false;
            }
        }
    }

    public static class UpdateSpaceInput implements Serializable {
        private String id;

        private String title;

        private List<String> members;

        private MemberOperation memberOperation;

        public UpdateSpaceInput(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public UpdateSpaceInput setId(String id) {
            this.id = id;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public UpdateSpaceInput setTitle(String title) {
            this.title = title;
            return this;
        }

        public List<String> getMembers() {
            return members;
        }

        public UpdateSpaceInput setMembers(List<String> members) {
            this.members = members;
            return this;
        }

        public MemberOperation getMemberOperation() {
            return memberOperation;
        }

        public UpdateSpaceInput setMemberOperation(MemberOperation memberOperation) {
            this.memberOperation = memberOperation;
            return this;
        }

        public void appendTo(StringBuilder _queryBuilder) {
            String separator = "";
            _queryBuilder.append('{');

            _queryBuilder.append(separator);
            separator = ",";
            _queryBuilder.append("id:");
            Query.appendQuotedString(_queryBuilder, id);

            if (title != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("title:");
                Query.appendQuotedString(_queryBuilder, title);
            }

            if (members != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("members:");
                _queryBuilder.append('[');

                String listSeperator1 = "";
                for (String item1 : members) {
                    _queryBuilder.append(listSeperator1);
                    listSeperator1 = ",";
                    Query.appendQuotedString(_queryBuilder, item1);
                }
                _queryBuilder.append(']');
            }

            if (memberOperation != null) {
                _queryBuilder.append(separator);
                separator = ",";
                _queryBuilder.append("memberOperation:");
                _queryBuilder.append(memberOperation.toString());
            }

            _queryBuilder.append('}');
        }
    }
}
