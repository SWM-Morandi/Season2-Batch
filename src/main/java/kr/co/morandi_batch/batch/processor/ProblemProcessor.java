package kr.co.morandi_batch.batch.processor;

import kr.co.morandi_batch.batch.dto.response.BojProblemInfoList;
import kr.co.morandi_batch.domain.problem.Problem;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class ProblemProcessor implements ItemProcessor<List<Problem>, List<Problem>> {
    private final String PREFIX = "https://solved.ac/api/v3/search/problem";

    @Override
    public List<Problem> process(List<Problem> item) throws Exception {
        String apiURI = getAPIURI(item);
//        getProblemInfo(apiURI);

        System.out.println("apiURI = " + apiURI);
        return null;
    }

    public String getAPIURI(List<Problem> problems) {
        StringBuffer sb = new StringBuffer().append(PREFIX).append("?query=");
        problems.forEach(problem -> {
            sb.append("id:");
            sb.append(problem.getBaekjoonProblemId());
            sb.append("|");
        });

        return sb.toString();
    }
    private List<BojProblemInfoList> getProblemInfo(String apiURI) {
        WebClient webClient = WebClient.builder()
                .baseUrl(apiURI)
                .build();
        String result = webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return parseProblemInfo(result);
    }
    private List<BojProblemInfoList> parseProblemInfo(String result) {
        return null;
    }
}
