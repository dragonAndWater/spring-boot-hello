package com.example.demo.util.excelUtil;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class EasyExcelUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EasyExcelUtil.class);
    private static final String EXCEL_FOLDER = "excel/";
    private static final String EXCEL_WRITE = "WRITE";
    private static final String EXCEL_FILL = "FILL";

    public EasyExcelUtil() {
    }

    public static List<Map<Integer, String>> syncRead(String filePath) {
        return EasyExcelFactory.read(filePath).sheet().doReadSync();
    }

    public static List<Map<Integer, String>> syncRead(String filePath, Integer sheetNo) {
        return EasyExcelFactory.read(filePath).sheet(sheetNo).doReadSync();
    }

    public static List<Map<Integer, String>> syncRead(InputStream inputStream, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(inputStream).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    public static List<Map<Integer, String>> syncRead(File file, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(file).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    public static List<Map<Integer, String>> syncRead(String filePath, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(filePath).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    public static List<T> syncReadModel(String filePath, Class clazz) {
        return EasyExcelFactory.read(filePath).sheet().head(clazz).doReadSync();
    }

    public static List<T> syncReadModel(String filePath, Class clazz, Integer sheetNo) {
        return EasyExcelFactory.read(filePath).sheet(sheetNo).head(clazz).doReadSync();
    }

    public static List<T> syncReadModel(InputStream inputStream, Class clazz, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(inputStream).sheet(sheetNo).headRowNumber(headRowNum).head(clazz).doReadSync();
    }

    public static List<T> syncReadModel(File file, Class clazz, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(file).sheet(sheetNo).headRowNumber(headRowNum).head(clazz).doReadSync();
    }

    public static List<T> syncReadModel(String filePath, Class clazz, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(filePath).sheet(sheetNo).headRowNumber(headRowNum).head(clazz).doReadSync();
    }

    public static void asyncRead(String filePath, ReadListener readListener) {
        EasyExcelFactory.read(filePath, readListener).sheet().doRead();
    }

    public static void asyncRead(String filePath, ReadListener readListener, Integer sheetNo) {
        EasyExcelFactory.read(filePath, readListener).sheet(sheetNo).doRead();
    }

    public static void asyncRead(InputStream inputStream, ReadListener readListener, Integer sheetNo, Integer headRowNum) {
        EasyExcelFactory.read(inputStream, readListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    public static void asyncRead(InputStream inputStream, Class classHead, ReadListener readListener, Integer sheetNo, Integer headRowNum) {
        EasyExcelFactory.read(inputStream, classHead, readListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    public static void asyncRead(File file, ReadListener readListener, Integer sheetNo, Integer headRowNum) {
        EasyExcelFactory.read(file, readListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    public static void asyncRead(String filePath, ReadListener readListener, Integer sheetNo, Integer headRowNum) {
        EasyExcelFactory.read(filePath, readListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    public static void asyncReadModel(String filePath, ReadListener readListener, Class clazz) {
        EasyExcelFactory.read(filePath, clazz, readListener).sheet().doRead();
    }

    public static void asyncReadModel(String filePath, ReadListener readListener, Class clazz, Integer sheetNo) {
        EasyExcelFactory.read(filePath, clazz, readListener).sheet(sheetNo).doRead();
    }

    public static void asyncReadModel(InputStream inputStream, ReadListener readListener, Class clazz, Integer sheetNo, Integer headRowNum) {
        EasyExcelFactory.read(inputStream, clazz, readListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    public static void asyncReadModel(File file, ReadListener readListener, Class clazz, Integer sheetNo, Integer headRowNum) {
        EasyExcelFactory.read(file, clazz, readListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    public static void asyncReadModel(String filePath, ReadListener readListener, Class clazz, Integer sheetNo, Integer headRowNum) {
        EasyExcelFactory.read(filePath, clazz, readListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    public static void write(String filePath, List<List<String>> head, List<List<Object>> data) {
        EasyExcel.write(filePath).head(head).sheet().doWrite(data);
    }

    public static void write(String filePath, List<List<String>> head, List<List<Object>> data, Integer sheetNo, String sheetName) {
        EasyExcel.write(filePath).head(head).sheet(sheetNo, sheetName).doWrite(data);
    }

    public static void writeTemplate(String filePath, String templateFileName, Class headClazz, List data) {
        EasyExcel.write(filePath, headClazz).withTemplate(templateFileName).sheet().doWrite(data);
    }

    public static void writeTemplate(String filePath, String templateFileName, List data) {
        EasyExcel.write(filePath).withTemplate(templateFileName).sheet().doWrite(data);
    }

    public static void write(String filePath, Class headClazz, List data) {
        EasyExcel.write(filePath, headClazz).sheet().doWrite(data);
    }

    public static void write(String filePath, Class headClazz, List data, Integer sheetNo, String sheetName) {
        EasyExcel.write(filePath, headClazz).sheet(sheetNo, sheetName).doWrite(data);
    }

    public static void write(HttpServletResponse response, Class headClazz, List data, WriteHandler writeHandler, String sheetName, String fileName) throws Exception {
        EasyExcel.write(getOutputStream(fileName, response), headClazz).registerWriteHandler(writeHandler).sheet(sheetName).doWrite(data);
    }

    public static void write(HttpServletResponse response, Class headClazz, List data, String sheetName, String fileName) throws Exception {
        EasyExcel.write(getOutputStream(fileName, response), headClazz).sheet(sheetName).doWrite(data);
    }

    public static void write(String filePath, Class headClazz, List data, WriteHandler writeHandler, Integer sheetNo, String sheetName) {
        EasyExcel.write(filePath, headClazz).registerWriteHandler(writeHandler).sheet(sheetNo, sheetName).doWrite(data);
    }

    public static void writeInclude(String filePath, Class headClazz, List data, Set<String> includeCols, String sheetName) {
        EasyExcel.write(filePath, headClazz).includeColumnFiledNames(includeCols).sheet(sheetName).doWrite(data);
    }

    public static void writeExclude(String filePath, Class headClazz, List data, Set<String> excludeCols, Integer sheetNo, String sheetName) {
        EasyExcel.write(filePath, headClazz).excludeColumnFiledNames(excludeCols).sheet(sheetNo, sheetName).doWrite(data);
    }

    public static void writeWithPullDown(HttpServletResponse response, Class classHead, String fileName, List excelData, String sheetName, final Integer firstRow, final Integer lastRow) throws Exception {
        final Map<Integer, String[]> explicitListConstraintMap = new HashMap();
        Field[] declaredFields = classHead.getDeclaredFields();

        for(int i = 0; i < declaredFields.length; ++i) {
            Field field = declaredFields[i];
            ExcelPullDownConstraint explicitConstraint = (ExcelPullDownConstraint)field.getAnnotation(ExcelPullDownConstraint.class);
            String[] explicitArray = resolveExplicitConstraint(explicitConstraint);
            if (explicitArray != null && explicitArray.length > 0) {
                explicitListConstraintMap.put(i, explicitArray);
            }
        }

        write((HttpServletResponse)response, classHead, excelData, new SheetWriteHandler() {
            @Override
            public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
            }

            @Override
            public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
                Sheet sheet = writeSheetHolder.getSheet();
                DataValidationHelper helper = sheet.getDataValidationHelper();
                explicitListConstraintMap.forEach((k, v) -> {
                    CellRangeAddressList rangeList = new CellRangeAddressList();
                    CellRangeAddress addr = new CellRangeAddress(firstRow == null ? 1 : firstRow, lastRow == null ? 500 : lastRow, k, k);
                    rangeList.addCellRangeAddress(addr);
                    DataValidationConstraint constraint = helper.createExplicitListConstraint(v);
                    DataValidation validation = helper.createValidation(constraint, rangeList);
                    sheet.addValidationData(validation);
                });
            }
        }, (String)sheetName, fileName);
    }

    private static String[] resolveExplicitConstraint(ExcelPullDownConstraint explicitConstraint) {
        if (explicitConstraint == null) {
            return null;
        } else {
            String[] source = explicitConstraint.source();
            if (source.length > 0) {
                return source;
            } else {
                Class<? extends ExcelPullDownExtension>[] classes = explicitConstraint.sourceClass();
                if (classes.length > 0) {
                    ExcelPullDownExtension explicitInterface = null;

                    try {
                        explicitInterface = (ExcelPullDownExtension)classes[0].newInstance();
                        String[] source1 = explicitInterface.source();
                        if (source1.length > 0) {
                            return source1;
                        }
                    } catch (InstantiationException var5) {
                        var5.printStackTrace();
                    } catch (IllegalAccessException var6) {
                        var6.printStackTrace();
                    }
                }

                return null;
            }
        }
    }

    public static void writeWithFillTemplate(String templateNameWithSuffix, String fileName, Integer sheetNo, HttpServletResponse response, Map<String, Object> fillMap, List data) {
        try {
            ExcelWriter excelWriter = getExcelWriter(templateNameWithSuffix, fileName, response);
            WriteSheet writeSheet = EasyExcel.writerSheet().sheetNo(sheetNo).build();
            if (fillMap != null) {
                excelWriter.fill(fillMap, writeSheet);
            }

            if (data != null) {
                excelWriter.fill(data, writeSheet);
            }

            excelWriter.finish();
        } catch (Exception var8) {
            resetResponse(response, var8);
        }

    }

    public static <T> void asyncReadWithoutReadListener(InputStream inputStream, Class classHead, int sheetNo, int headRowNum, final int threshold, final String methodName, final Object objectInstance) {
        if (objectInstance == null) {
            throw new RuntimeException("对象实例不能为空!");
        } else {
            EasyExcelFactory.read(inputStream, classHead, new AnalysisEventListener<T>() {
                private LinkedList<T> linkedList = new LinkedList();

                @Override
                public void invoke(T t, AnalysisContext context) {
                    this.linkedList.add(t);
                    if (this.linkedList.size() == threshold) {
                        EasyExcelUtil.LOGGER.info("达到指定阀值:{}执行批量操作", threshold);
                        EasyExcelUtil.getMethodAndInvoke(objectInstance, methodName, this.linkedList);
                        this.linkedList.clear();
                    }

                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    EasyExcelUtil.LOGGER.info("执行结束 linkedList.size:{}", this.linkedList.size());
                    if (this.linkedList.size() > 0) {
                        EasyExcelUtil.LOGGER.info("处理最后的数据:{}", this.linkedList.size());
                        EasyExcelUtil.getMethodAndInvoke(objectInstance, methodName, this.linkedList);
                        this.linkedList.clear();
                    }

                }
            }).sheet(sheetNo).headRowNumber(headRowNum).doRead();
        }
    }

    public static void writeWithTemplate(List data, String templateNameWithSuffix, String fileName, Integer sheetNo, HttpServletResponse response, WriteHandler writeHandler) {
        try {
            OutputStream outputStream = getOutputStream(fileName, response);
            String templateFileAbsoluteUrl = getUrlPath(templateNameWithSuffix);
            if (writeHandler == null) {
                EasyExcel.write(outputStream).withTemplate(templateFileAbsoluteUrl).sheet(sheetNo).doWrite(data);
            } else {
                EasyExcel.write(outputStream).withTemplate(templateFileAbsoluteUrl).registerWriteHandler(writeHandler).sheet(sheetNo).doWrite(data);
            }
        } catch (Exception var8) {
            resetResponse(response, var8);
        }

    }

    private static String getUrlPath(String templateNameWithSuffix) throws Exception {
        URL url = Thread.currentThread().getContextClassLoader().getResource("excel/" + templateNameWithSuffix);
        if (url == null) {
            throw new FileNotFoundException("在类路径下excel/文件夹中未获取到该模板名称【" + templateNameWithSuffix + "】的模板文件");
        } else {
            return url.getPath();
        }
    }

    public static void write(HttpServletResponse response, Class headClazz, String fileName, String sheetName, String methodName, Object serviceObjectInstance, Object requestParams, WriteHandler writeHandler) throws Exception {
        if (serviceObjectInstance == null) {
            throw new RuntimeException("对象实例不能为空!");
        } else {
            ExcelWriter excelWriter = EasyExcel.write(getOutputStream(fileName, response), headClazz).build();
            WriteSheet writeSheet;
            if (writeHandler == null) {
                writeSheet = EasyExcel.writerSheet().sheetName(sheetName).build();
            } else {
                writeSheet = EasyExcel.writerSheet().sheetName(sheetName).registerWriteHandler(writeHandler).build();
            }

            executeQueryAndWrite(methodName, serviceObjectInstance, requestParams, excelWriter, writeSheet, "WRITE");
        }
    }

    private static void executeQueryAndWrite(String methodName, Object objectInstance, Object requestParams, ExcelWriter excelWriter, WriteSheet writeSheet, String methodType) {
        int pageNo = 1;
        short pageSize = 500;

        while(true) {
            List result = (List)getMethodAndInvoke(objectInstance, methodName, requestParams, pageNo, Integer.valueOf(pageSize));
            if (CollectionUtils.isEmpty(result)) {
                if (pageNo == 1) {
                    if (methodType.equals("WRITE")) {
                        excelWriter.write(new ArrayList(1), writeSheet);
                    } else if (methodType.equals("FILL")) {
                        excelWriter.fill(new ArrayList(1), writeSheet);
                    }
                }

                excelWriter.finish();
                return;
            }

            ++pageNo;
            if (methodType.equals("WRITE")) {
                excelWriter.write(result, writeSheet);
            } else if (methodType.equals("FILL")) {
                excelWriter.fill(result, writeSheet);
            }

            result.clear();
        }
    }

    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException var3) {
            resetResponse(response, var3);
            return null;
        }
    }

    private static Object getMethodAndInvoke(Object instance, String methodName, List data) {
        try {
            Method m = instance.getClass().getDeclaredMethod(methodName, List.class);
            return m.invoke(instance, data);
        } catch (NoSuchMethodException var4) {
            throw new RuntimeException("根据方法名:[" + methodName + "],未匹配到参数类型为List的方法");
        } catch (Exception var5) {
            throw new RuntimeException("调用目标方法[" + methodName + "]异常:", var5);
        }
    }

    private static Object getMethodAndInvoke(Object instance, String methodName, Object requestParams, Integer pageNo, Integer pageSize) {
        try {
            Method m = instance.getClass().getDeclaredMethod(methodName, requestParams.getClass(), Integer.class, Integer.class);
            return m.invoke(instance, requestParams, pageNo, pageSize);
        } catch (NoSuchMethodException var6) {
            throw new RuntimeException("根据方法名:[" + methodName + "],未匹配到参数类型为[" + requestParams.getClass() + "、Integer、Integer]的方法");
        } catch (Exception var7) {
            throw new RuntimeException("调用目标方法[" + methodName + "]异常:", var7);
        }
    }

    private static void resetResponse(HttpServletResponse response, Exception e) {
        response.reset();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        throw new RuntimeException("导出Excel异常:", e);
    }

    public static void writeWithTemplate(String templateNameWithSuffix, String fileName, Integer sheetNo, HttpServletResponse response, WriteHandler writeHandler, Object objectInstance, String methodName, Object requestParams) throws Exception {
        if (objectInstance == null) {
            throw new RuntimeException("对象实例不能为空!");
        } else {
            try {
                ExcelWriter excelWriter = getExcelWriter(templateNameWithSuffix, fileName, response);
                WriteSheet writeSheet;
                if (writeHandler == null) {
                    writeSheet = EasyExcel.writerSheet().sheetNo(sheetNo).build();
                } else {
                    writeSheet = EasyExcel.writerSheet().sheetNo(sheetNo).registerWriteHandler(writeHandler).build();
                }

                executeQueryAndWrite(methodName, objectInstance, requestParams, excelWriter, writeSheet, "WRITE");
            } catch (Exception var10) {
                resetResponse(response, var10);
            }

        }
    }

    public static void writeWithFillTemplate(String templateNameWithSuffix, String fileName, Integer sheetNo, HttpServletResponse response, Map<String, Object> fillMap, Object objectInstance, String methodName, Object requestParams) {
        if (objectInstance == null) {
            throw new RuntimeException("对象实例不能为空!");
        } else {
            try {
                ExcelWriter excelWriter = getExcelWriter(templateNameWithSuffix, fileName, response);
                WriteSheet writeSheet = EasyExcel.writerSheet().sheetNo(sheetNo).build();
                if (fillMap != null) {
                    excelWriter.fill(fillMap, writeSheet);
                }

                executeQueryAndWrite(methodName, objectInstance, requestParams, excelWriter, writeSheet, "FILL");
            } catch (Exception var10) {
                resetResponse(response, var10);
            }

        }
    }

    private static ExcelWriter getExcelWriter(String templateNameWithSuffix, String fileName, HttpServletResponse response) throws Exception {
        OutputStream outputStream = getOutputStream(fileName, response);
        String templateFileAbsoluteUrl = getUrlPath(templateNameWithSuffix);
        return EasyExcel.write(outputStream).withTemplate(templateFileAbsoluteUrl).build();
    }
}
