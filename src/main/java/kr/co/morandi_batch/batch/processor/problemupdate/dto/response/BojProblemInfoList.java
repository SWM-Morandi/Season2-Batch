package kr.co.morandi_batch.batch.processor.problemupdate.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class BojProblemInfoList {
    int count;
    List<BojProblemInfo> items;

}
