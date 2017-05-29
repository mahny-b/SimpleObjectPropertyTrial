package jp.gr.java_conf.mahny.simple_object_property_trial;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * 主にSimpleObjectProperty系統の使い方
 * @author mahny
 */
public class SimpleObjectPropertyTrial {

	/**
	 * 入出力
	 */
	@Test
	public void trySimpleStringProperty1() {

		SimpleStringProperty strProp = new SimpleStringProperty();
		assertThat(strProp.get(), nullValue());

		strProp = new SimpleStringProperty("hoge");
		assertThat(strProp.get(), is("hoge"));

		strProp.set("moge");
		assertThat(strProp.get(), is("moge"));
	}

	/**
	 * 無名クラスでリスナー追加
	 */
	@Test
	public void trySimpleStringProperty2() {

		SimpleStringProperty strProp = new SimpleStringProperty();
		ChangeListener<String> listener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				assertThat(oldValue, nullValue());
				assertThat(newValue, is("hoge"));
			}
		};
		strProp.addListener(listener);

		strProp.set("hoge");
		assertThat(strProp.get(), is("hoge"));

		strProp.removeListener(listener);
	}

	/**
	 * 自作クラスでリスナー追加
	 */
	@Test
	public void trySimpleStringProperty3() {

		SimpleStringProperty strProp = new SimpleStringProperty();
		ChangeListener<String> listener = new ChangeListenerEx();
		strProp.addListener(listener);

		strProp.set("hoge");
		assertThat(strProp.get(), is("hoge"));

		strProp.removeListener(listener);
	}
	/**
	 * trySimpleStringProperty3用クラス
	 * @author mahny
	 */
	private class ChangeListenerEx implements ChangeListener<String> {
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			assertThat(oldValue, nullValue());
			assertThat(newValue, is("hoge"));
		}
	}
}
