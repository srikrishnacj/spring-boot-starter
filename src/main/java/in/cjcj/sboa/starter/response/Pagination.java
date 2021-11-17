package in.cjcj.sboa.starter.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int pageNumber;
    private int pageSize;
    private long totalElements;
}
