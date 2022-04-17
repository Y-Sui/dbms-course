package dbms.suiyuan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author suiyuan
 * @description: 生成表格的类
 */
public class TableGenerator {
    // 间隔padding
    private static final int PADDING_SIZE = 2;

    /**
     * @param headerList
     * @param rowsList
     * @param headerHeight
     * @return
     * @description: 根据内容创建表格
     */
    public static String generateTable(List<String> headerList, List<List<String>> rowsList,
                                       int... headerHeight) {
        StringBuilder stringBuilder = new StringBuilder();

        int rowHeight = headerHeight.length > 0 ? headerHeight[0] : 1;

        Map<Integer, Integer> colMaxWidth = getMaxWidth(headerList, rowsList);

        createRowLine(stringBuilder, headerList.size(), colMaxWidth);
        String NEW_LINE = "\n"; // 换行符
        stringBuilder.append(NEW_LINE);

        for (int headerIndex = 0; headerIndex < headerList.size(); headerIndex++) {
            fillCell(stringBuilder, headerList.get(headerIndex), headerIndex, colMaxWidth);
        }
        stringBuilder.append(NEW_LINE);
        createRowLine(stringBuilder, headerList.size(), colMaxWidth);

        for (List<String> row : rowsList) {
            for (int i = 0; i < rowHeight; i++) {
                stringBuilder.append(NEW_LINE);
            }

            for (int cellIndex = 0; cellIndex < row.size(); cellIndex++) {
                fillCell(stringBuilder, row.get(cellIndex), cellIndex, colMaxWidth);
            }
        }

        stringBuilder.append(NEW_LINE);
        createRowLine(stringBuilder, headerList.size(), colMaxWidth);
        return stringBuilder.toString();
    }

    private static void fillSpace(StringBuilder stringBuilder, int length) {
        for (int i = 0; i < length - 1; i++) {
            stringBuilder.append(" ");
        }
    }

    private static void createRowLine(StringBuilder stringBuilder, int headersListSize,
                                      Map<Integer, Integer> columnMaxWidthMapping) {
        for (int i = 0; i < headersListSize; i++) {
            String TABLE_JOINT_SYMBOL = "+";
            if (i == 0) {
                stringBuilder.append(TABLE_JOINT_SYMBOL);
            }

            for (int j = 0; j < columnMaxWidthMapping.get(i) + PADDING_SIZE * 2; j++) {
                String TABLE_H_SPLIT_SYMBOL = "-";
                stringBuilder.append(TABLE_H_SPLIT_SYMBOL);
            }
            stringBuilder.append(TABLE_JOINT_SYMBOL);
        }
    }

    private static Map<Integer, Integer> getMaxWidth(List<String> headersList, List<List<String>> rowsList) {
        Map<Integer, Integer> columnMaxWidthMapping = new HashMap<>();

        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {
            columnMaxWidthMapping.put(columnIndex, 0);
        }

        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {

            if (headersList.get(columnIndex).length() > columnMaxWidthMapping.get(columnIndex)) {
                columnMaxWidthMapping.put(columnIndex, headersList.get(columnIndex).length());
            }
        }

        for (List<String> row : rowsList) {
            for (int columnIndex = 0; columnIndex < row.size(); columnIndex++) {
                if (row.get(columnIndex).length() > columnMaxWidthMapping.get(columnIndex)) {
                    columnMaxWidthMapping.put(columnIndex, row.get(columnIndex).length());
                }
            }
        }

        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {
            if (columnMaxWidthMapping.get(columnIndex) % 2 != 0) {
                columnMaxWidthMapping.put(columnIndex, columnMaxWidthMapping.get(columnIndex) + 1);
            }
        }
        return columnMaxWidthMapping;
    }

    private static int getOptimumCellPadding(int cellIndex, int datalength, Map<Integer, Integer> columnMaxWidthMapping,
                                             int cellPaddingSize) {
        if (datalength % 2 != 0) {
            datalength++;
        }
        if (datalength < columnMaxWidthMapping.get(cellIndex)) {
            cellPaddingSize = cellPaddingSize + (columnMaxWidthMapping.get(cellIndex) - datalength) / 2;
        }
        return cellPaddingSize;
    }

    private static void fillCell(StringBuilder stringBuilder, String cell, int cellIndex,
                                 Map<Integer, Integer> colMaxWidth) {

        int cellPaddingSize = getOptimumCellPadding(cellIndex, cell.length(), colMaxWidth, PADDING_SIZE);

        String TABLE_V_SPLIT_SYMBOL = "|"; //分隔符
        if (cellIndex == 0) {
            stringBuilder.append(TABLE_V_SPLIT_SYMBOL);
        }

        fillSpace(stringBuilder, 2);
        stringBuilder.append(cell);
        if (cell.length() % 2 != 0) {
            stringBuilder.append(" ");
        }

        fillSpace(stringBuilder, cellPaddingSize);
        fillSpace(stringBuilder, cellPaddingSize);
        fillSpace(stringBuilder, 2);

        stringBuilder.append(TABLE_V_SPLIT_SYMBOL);
    }
}