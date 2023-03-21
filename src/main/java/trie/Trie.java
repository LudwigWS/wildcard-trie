package trie;

import java.util.*;
import java.util.Map.Entry;


public class Trie implements TrieInterface {
	private static final int FIELD_NUM = 10;
	/**
	 * starting node of the trie
	 */
	private final Node root;
	/**
	 * the number of unique words in the trie
	 */
	private int size;
	/**
	 * (matches any single character)
	 */
	public static final char WILDCARD_QUESTION_MARK = '?';
	/**
	 * matches any sequence of characters
	 */
	public static final char WILDCARD_STAR = '*';

	/**
	 * Constructs a new empty trie
	 */
	public Trie() {
		root = new Node();
		size = 0;
	}

	/* construct the trie from a Collection of Strings */
	public Trie(Collection<String> col) {
		this();
		for (String s : col) {
			this.add(s);
		}
	}

	/* construct the trie from an array of Strings */
	public Trie(String[] arr) {
		this();
		for (String s : arr) {
			this.add(s);
		}
	}

	/* adds a word to the trie */
	@Override
	public void add(String str) {
		Node curr = root;

		String[] fields = str.split("\\|", -1);

		if (fields.length != FIELD_NUM) {
			return;
		}

		for (int i = 0; i < fields.length; i++) {
			if (curr.children.get(fields[i]) == null) {
				curr.children.put(fields[i], new Node());
			}
			curr = curr.children.get(fields[i]);
		}
		if (!curr.isEnd) {
			size++;
		}
		curr.isEnd = true;
	}

	@Override
	public boolean contains(String str) {
		return false;
	}

	@Override
	public List<String> prefixedWords(String prefix) {
		return null;
	}


	/* returns a set of all words matching the string with wildcards */
	@Override
	public Set<String> wildcardMatches(String pattern) {
		Set<String> wildcardMatches = new HashSet<>();
		String[] fieldsPattern = pattern.split("\\|", -1);
		wildcardTraverse(fieldsPattern, new LinkedList<String>(), root, 0, wildcardMatches);
		return wildcardMatches;
	}

	/*
	 * traverses the trie and adds all words matching the string with wildcards * to
	 * list
	 */
	private void wildcardTraverse(String[] fieldsPattern, LinkedList<String> prefix, Node root, int fieldIndex,
			Set<String> wildcardMatches) {
		if (root == null) {
			return;
		}
		if (fieldIndex == fieldsPattern.length) {
			if (root.isEnd) {
				wildcardMatches.add(String.join("|", prefix));
			}
			return;
		}

		prefix.add(fieldsPattern[fieldIndex]);
		wildcardTraverse(fieldsPattern, prefix, root.children.get(fieldsPattern[fieldIndex]), fieldIndex + 1,
				wildcardMatches);

		prefix.removeLast();


		if (root.children.get("%") != null) {
			prefix.add("%");
			wildcardTraverse(fieldsPattern, prefix, root.children.get("%"), fieldIndex + 1,
					wildcardMatches);
			prefix.removeLast();
		}
	}





	/* remove all elements from the trie */
	@Override
	public void clear() {
		root.children.clear();
		size = 0;
	}


	@Override
	public int size() {
		return size;
	}

	@Override
	public void remove(String str) {

	}

}