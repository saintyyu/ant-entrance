package problem.three.dto;

/**
 * Created by cdyujing7 on 2018/9/12.
 * 元数据
 */
public class Datum {
    private String id;
    private String groupId;
    private Float quota;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Float getQuota() {
        return quota;
    }

    public void setQuota(Float quota) {
        this.quota = quota;
    }
}
