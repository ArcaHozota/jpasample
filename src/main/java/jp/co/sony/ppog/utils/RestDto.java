package jp.co.sony.ppog.utils;

import lombok.Data;

/**
 * 統一AJAX請求返回結果類；
 *
 * @author Administrator
 */
@Data
public class RestDto<Type> {

    /**
     * 處理成功的信息
     */
    private static final String SUCCESS = "SUCCESS";

    /**
     * 處理失敗的信息
     */
    private static final String FAILURE = "FAILURE";

    /**
     * 處理的結果： 成功：SUCCESS，失敗：ERROR
     */
    private String status;

    /**
     * 請求成功與否的信息；
     */
    private String message;

    /**
     * 返回的數據；
     */
    private Type data;

    /**
     * 請求成功時使用的工具方法；
     *
     * @param <Type> 數據類型
     * @param object 返回的數據；
     * @return ResponseDto
     */
    public static <Type> RestDto<Type> success(final Type object) {
        final RestDto<Type> restDto = new RestDto<>();
        restDto.status = SUCCESS;
        restDto.data = object;
        return restDto;
    }

    /**
     * 請求失敗時使用的工具方法；
     *
     * @param <Type>  數據類型
     * @param message 失敗的處理信息；
     * @return ResponseDto
     */
    public static <Type> RestDto<Type> failure(String message) {
        final RestDto<Type> restDto = new RestDto<>();
        restDto.status = FAILURE;
        restDto.message = message;
        return restDto;
    }

    /**
     * 無參數構造器
     */
    public RestDto() {
        super();
    }

    /**
     * 全參數構造器
     *
     * @param status  當前請求的處理結果
     * @param message 請求成功與否的信息
     * @param data    返回的數據
     */
    public RestDto(String status, String message, Type data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }
}