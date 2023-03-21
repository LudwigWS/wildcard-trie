package trie;

import java.util.Arrays;
import java.util.Comparator;

public class Example {

	public static void main(String[] args) {
		Trie t = new Trie(new String[] { "cafe", "coffee", "caffe", "cup", "java", "kaffe", "kaf%jj", "%af%jj" });

		System.out.println(t.wildcardMatches("c*"));
		// [cafe, caffe, cup, coffee]
		System.out.println(t.wildcardMatches("c*e*"));
		// [cafe, caffe, coffee]
		System.out.println(t.wildcardMatches("?affe"));
		// [caffe, kaffe]
		System.out.println(Arrays.toString(t.wildcardMatches("kafejj").stream().sorted(Comparator.reverseOrder()).toArray()));
	}

}
