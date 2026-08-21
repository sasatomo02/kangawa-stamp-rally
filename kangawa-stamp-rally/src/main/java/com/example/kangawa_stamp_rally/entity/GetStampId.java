package com.example.kangawa_stamp_rally.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable // エンティティに埋め込み可能なIDクラスであることを示す
public class GetStampId implements Serializable {

    private String uuid;
    private String stampNo;

    public GetStampId() {}

    // コンストラクタ
    public GetStampId(String uuid, String stampNo) {
        this.uuid = uuid;
        this.stampNo = stampNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetStampId that = (GetStampId) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(stampNo, that.stampNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, stampNo);
    }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
    public String getStampNo() { return stampNo; }
    public void setStampNo(String stampNo) { this.stampNo = stampNo; }
}