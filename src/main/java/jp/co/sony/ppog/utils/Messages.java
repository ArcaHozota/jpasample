package jp.co.sony.ppog.utils;


/**
 * 共通メッセージクラス
 *
 * @author Administrator
 * @since 2023-07-28
 */
public final class Messages {

	private Messages() {
		throw new IllegalStateException("Utility class");
	}

	public static final String MSG001 = "拡張メッセージコンバーターの設置は完了しました。";

	public static final String MSG002 = "静的リソースのマッピングが開始しました。";

	public static final String MSG003 = "アプリは正常に起動しました!";

	public static final String MSG004 = "入力した都市名が重複する。";

	public static final String MSG005 = "入力した都市名は4桁から23桁までのローマ字にしなければなりません。";
}
