package com.example.board.dto;

/**
 * [문항5] 페이징 처리용 클래스
 * - 현재 페이지, 한 페이지에 보여줄 글 수, 전체 글 수를 받아
 *   SQL 에 필요한 값(offset)과 화면에 필요한 값(시작/끝 페이지 번호, 이전/다음)을 계산
 *
 *  예) page=2, size=10  ->  offset=10  ->  11번째 글부터 10개 조회
 */
public class Page {

    private int page;              // 현재 페이지 번호 (1부터 시작)
    private int size;              // 한 페이지에 보여줄 게시글 수
    private int total;             // 전체 게시글 수
    private int blockSize = 5;     // 화면 하단에 보여줄 페이지 번호 개수 (예: 1 2 3 4 5)

    public Page() {
        this(1, 10, 0);
    }

    public Page(int page, int size, int total) {
        this.size = (size < 1) ? 10 : size;
        this.total = Math.max(total, 0);
        int lastPage = getTotalPages();
        // 잘못된 페이지 번호가 들어오면 범위 안으로 보정
        if (page < 1) {
            page = 1;
        } else if (lastPage > 0 && page > lastPage) {
            page = lastPage;
        }
        this.page = page;
    }

    // SQL LIMIT 에 쓰는 시작 위치 : (현재 페이지 - 1) * 페이지당 글 수
    public int getOffset() {
        return (page - 1) * size;
    }

    // 전체 페이지 수
    public int getTotalPages() {
        return (int) Math.ceil((double) total / size);
    }

    // 하단 페이지 번호 블록의 시작 번호  (예: 현재 7페이지 -> 6)
    public int getStartPage() {
        return ((page - 1) / blockSize) * blockSize + 1;
    }

    // 하단 페이지 번호 블록의 끝 번호  (예: 현재 7페이지 -> 10, 전체가 8페이지면 8)
    public int getEndPage() {
        int end = getStartPage() + blockSize - 1;
        return Math.min(end, Math.max(getTotalPages(), 1));
    }

    // [이전] 버튼 표시 여부
    public boolean isPrev() {
        return getStartPage() > 1;
    }

    // [다음] 버튼 표시 여부
    public boolean isNext() {
        return getEndPage() < getTotalPages();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }
}
