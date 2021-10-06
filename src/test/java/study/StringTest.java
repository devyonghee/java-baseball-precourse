package study;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringTest {

	@Test
	void split() {
		assertThat("1,2".split(","))
			.hasSize(2)
			.containsExactly("1", "2");
	}

	@Test
	void split_onlyOne() {
		assertThat("1".split(","))
			.hasSize(1)
			.containsExactly("1");
	}

	@Test
	void substring() {
		String str = "(1,2)";
		assertThat(str.substring(1, str.length() - 1))
			.isEqualTo("1,2");
	}

	@Test
	@DisplayName("범위를 벗어날 난 문자를 구할 경우 예외가 발생하는지 확인")
	void charAt() {
		ThrowingCallable throwingCallable = () -> "abc".charAt(Integer.MAX_VALUE);

		assertThatThrownBy(throwingCallable)
			.isExactlyInstanceOf(StringIndexOutOfBoundsException.class)
			.hasMessageContaining("String index out of range");

		assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
			.isThrownBy(throwingCallable)
			.withMessageContaining("String index out of range");
	}
}
