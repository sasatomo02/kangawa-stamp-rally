package com.example.kangawa_stamp_rally.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable // エンティティに埋め込み可能なIDクラスであることを示す
public class GetStampId implements Serializable {

    // ユーザーのUUIDとスタンプのSTAMP_NOをフィールドとして持つ
    // これらのフィールド名は、UserEntityとStampEntityの主キーのフィールド名と一致させるのが一般的
    private String uuid; // UserEntityの主キー(UUID)に対応
    private String stampNo; // StampEntityの主キー(STAMP_NO)に対応

    // デフォルトコンストラクタはJPAの要件
    public GetStampId() {}

    // コンストラクタ
    public GetStampId(String uuid, String stampNo) {
        this.uuid = uuid;
        this.stampNo = stampNo;
    }

    // --- 非常に重要: hashCode() と equals() を実装する ---
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

    // getter と setter
    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
    public String getStampNo() { return stampNo; }
    public void setStampNo(String stampNo) { this.stampNo = stampNo; }
}