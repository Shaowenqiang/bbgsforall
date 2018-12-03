package cn.com.hqep.bidder.service;

import java.io.OutputStream;
import java.util.Map;

/**
 *
 * 汇总报告word导出
 * @author swq
 * @date
 */
public interface wordExportService {
    Map export(OutputStream outputStream,String sourcePath);

    boolean quertSort();
}
