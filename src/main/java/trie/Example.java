package trie;

import java.util.Arrays;
import java.util.Comparator;

public class Example {

	public static void main(String[] args) {
		Trie t = new Trie(new String[] { "201412|%|%|CNY|%|%|%|2|%|%",
				"121026|%|%|%|1|%|%|%|%|%",
		});

		System.out.println(t.wildcardMatches("121026|1|2|3|1|4|5|6|7|9"));
		System.out.println(t.wildcardMatches("c*"));
		// [cafe, caffe, cup, coffee]
		System.out.println(t.wildcardMatches("c*e*"));
		// [cafe, caffe, coffee]
		System.out.println(t.wildcardMatches("?affe"));
		// [caffe, kaffe]
		System.out.println(Arrays.toString(t.wildcardMatches("kafejj").stream().sorted(Comparator.reverseOrder()).toArray()));
	}

}
