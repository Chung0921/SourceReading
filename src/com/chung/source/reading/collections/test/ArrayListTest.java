package com.chung.source.reading.collections.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chung.
 * Usage:
 * Description:
 * Create dateTime: 17/5/8
 */
public class ArrayListTest {

	private static final List<String> sources = new ArrayList<>();

	private static final List<String> sourcesWithNull = new ArrayList<>();

	static {
		sources.add( "tmp1" );
		sources.add( "tmp2" );
		sources.add( "tmp3" );
		sources.add( "tmp4" );
		sources.add( "tmp5" );
		sourcesWithNull.addAll( sources );
		sourcesWithNull.add( null );
	}

	public static void main( String[] args ) {
//		testTransferToArray();

//		tesAddAll();

		tesSubList();
	}

	//test list transfer to Array

	private static void testTransferToArray() {

		System.out.println( "list not contain null elements------>" );
		transferListToArray( sources );
		System.out.println( "list contain null elements------>" );
		transferListToArray( sourcesWithNull );

		System.out.println( "=====================================" );
		System.out.println( "list not contain null elements------>" );
		transferListToArrayAllocateSize( sources );
		System.out.println( "list contain null elements------>" );
		transferListToArrayAllocateSize( sourcesWithNull );

	}


	/**
	 * 将集合转换为数组 自动进行转换
	 */
	private static void transferListToArray( List<String> sources ) {
		System.out.println( "Auto array size to transfer." );
		System.out.println( "source list.size is:" + sources.size() );
		System.out.println( "sources.toArray( destArray );" );
		String[] destArray = sources.toArray( new String[0] );
		System.out.println( "list to array, length is:" + destArray.length );
		for ( String s : destArray ) {
			System.out.println( " element is:" + s );
		}
	}


	/**
	 * 将集合转换为数组 指定新生成数组大小后进行转换
	 */
	private static void transferListToArrayAllocateSize( List<String> sources ) {
		System.out.println( "Allocate array size to transfer." );
		System.out.println( "source list.size is:" + sources.size() );
		System.out.println( "new String array length is 10." );
		System.out.println( "sources.toArray( destArray );" );
		String[] destArray = new String[10];
		sources.toArray( destArray );
		System.out.println( "list to array, length is:" + destArray.length );
		for ( String s : destArray ) {
			System.out.println( " element is:" + s );
		}
	}

	// test to addAll
	private static void tesAddAll() {
		System.out.println( "test addAll() with list contain duplicate elements------>" );
		tesAddAllWithDuplicateElement();
		System.out.println( "test addAll() it self------>" );
		tesAddAllWithItSelf();
	}

	/**
	 * 向集合中添加重复元素
	 */
	private static void tesAddAllWithDuplicateElement() {
		List<String> tmp = sources;
		boolean result = tmp.addAll( sourcesWithNull );
		System.out.println( result );
		tmp.forEach( System.out::println );
	}

	/**
	 * 向集合中添加其本身
	 */
	private static void tesAddAllWithItSelf() {
		List<String> tmp = sources;
		boolean result = tmp.addAll( tmp );
		System.out.println( result );
		tmp.forEach( System.out::println );
	}

	/**
	 * 测试截取列表
	 * 替换子列表中相同位置的元素
	 * 观察原有列表是否更改
	 */
	private static void tesSubList() {
		System.out.println( "origin ->" );
		sources.forEach( System.out::println );
		List<String> tmp = sources.subList( 0, sources.size() );
		System.out.println( "after sublist ->" );
		tmp.forEach( System.out::println );
		for ( int index = 0 ; index < tmp.size() ; index++ ) {
			String element = tmp.get( index );
			String after = element + "_changed";
			tmp.set( index, after );
			System.out.println( "change index:" + index + " from " + element + " to " + after );
		}
		System.out.println( "after replace" );
		sources.forEach( System.out::println );
	}
}
