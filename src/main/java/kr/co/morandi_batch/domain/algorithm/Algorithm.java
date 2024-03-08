package kr.co.morandi_batch.domain.algorithm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.co.morandi_batch.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Algorithm extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long algorithmId;

    private Integer bojTagId;

    private String algorithmKey;

    private String algorithmName;

    @Builder
    private Algorithm(Integer bojTagId, String algorithmKey, String algorithmName) {
        this.bojTagId = bojTagId;
        this.algorithmKey = algorithmKey;
        this.algorithmName = algorithmName;
    }
}
