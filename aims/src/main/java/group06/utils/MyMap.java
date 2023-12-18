package group06.utils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Lớp {@link utils.MyMap JSON} đại diện cho các đối tượng JSON. 
 * Để tạo một đối tượng JSON mới,
 * JSON jsonObject = new JSON();
 * jsonObject.put("key", value);
 * 
 * @author hieud
 *
 */
public class MyMap extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	/**
	 * Trả về một {@link java.lang.String String} biểu diễn đối tượng JSON.
	 * 
	 * @author hieudm
	 *         https://hg.openjdk.java.net/jdk8/jdk8/jdk/file/tip/src/share/classes/java/util/Hashtable.java
	 * @return một {@link java.lang.String String}.
	 */
	// Functional cohesion do thực hiện các chức năng cần thiết của MyMap
	public String toJSON() {
		// ...
	}

	/**
	 * Trả về một {@link java.util.Map Map} biểu diễn sự ánh xạ giữa tên thuộc tính và giá trị của một đối tượng.
	 * 
	 * @author hieudm
	 *         https://stackoverflow.com/questions/52406467/convert-object-to-map-in-java
	 * @param obj - một {@link java.lang.Object Object} tùy ý.
	 * @return một {@link java.util.Map Map} ánh xạ tên thuộc tính và giá trị tương ứng.
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	// Functional cohesion do thực hiện các chức năng cần thiết của MyMap
	public static Map<String, Object> toMyMap(Object obj) throws IllegalArgumentException, IllegalAccessException {
		// ...
	}

	// Các phương thức khác cũng có Functional cohesion do thực hiện các chức năng cần thiết của MyMap
	// ...

	// VI PHẠM NGUYÊN LÝ SOLID: Có thể chia thành các lớp riêng để quản lý các chức năng khác nhau.
	// VI PHẠM NGUYÊN LÝ SOLID: Có thể tạo một lớp riêng quản lý việc đọc và giải mã JSON thay vì tích hợp trong MyMap.
	// VI PHẠM NGUYÊN LÝ SOLID: Có thể tách riêng lớp MyMap và lớp đọc JSON để đảm bảo độc lập và chịu trách nhiệm.
	// VI PHẠM NGUYÊN LÝ SOLID: Có thể tạo một giao diện hoặc lớp riêng để xử lý chuỗi JSON thay vì tích hợp trong MyMap.
}
