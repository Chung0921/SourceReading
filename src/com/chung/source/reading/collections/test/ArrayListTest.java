package com.chung.source.reading.collections.test;

import com.chung.source.reading.collections.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

//		tesSubList();

//		testInit();

//		testClone();

//		testToArray();

//		testSysCpyArray();

		testBatchRemove();
	}

	/**
	 * 测试私有方法在retainAll及removeAll时的调用情况
	 *
	 */
	private static void testBatchRemove(){
		ArrayList<User> users = new ArrayList<>();
		String namePrefix = "Chung:";
		for ( int i = 0 ; i < 10 ; i++ ) {
			users.add( new User()
					.setId( Integer.toUnsignedLong( i + 1 ) )
					.setName( namePrefix + Integer.toString( i ) )
					.setScore( new Random().nextDouble() )
					.setStatus( User.STATUS_NORMAL )
			);
		}
		ArrayList<User> toRemove = new ArrayList<>(  );
		ArrayList<User> toRetain = new ArrayList<>(  );
		for ( int i = 0 ; i < 10 ; i++ ) {
			if(i>2&&i<8){
				toRemove.add( users.get( i ) );
				toRetain.add( users.get( i ) );
			}

		}

//		users.removeAll( toRemove );
		users.retainAll( toRetain );


	}

	/**
	 * 测试rm时为何需要指定最后一个元素置null 触发gc回收
	 * 结论 使用java.lang.System#arraycopy(java.lang.Object, int, java.lang.Object, int, int) 时
	 * 仅对区间范围内的元素进行处理,并不处理区间之外的原有元素 所以需要手动赋值为null才会引发gc进行原有对象的回收
	 *
	 */
	private static void testSysCpyArray() {
		User[] users = new User[6];
		//init with user
		for ( int i = 0 ; i < users.length ; i++ ) {
			users[i] = new User()
					.setId( Integer.toUnsignedLong( i + 1 ) )
					.setName( Integer.toString( i ) )
					.setScore( new Random().nextDouble() )
					.setStatus( User.STATUS_NORMAL );
		}
		int index = 1;
		int numMoved = users.length - index - 1;
		System.arraycopy( users, index + 1, users, index,
				numMoved );
		for ( User user : users ) {
			System.out.println( user );
		}

	}

	/**
	 * 测试 DEFAULTCAPACITY_EMPTY_ELEMENTDATA 和 EMPTY_ELEMENTDATA 的区别
	 * 并观察扩容过程
	 * <p>
	 * 如果初始化时指定list长度为0 那么如果向其添加元素时 开始1.5倍扩容 size变化:1,2,3,6,9,13
	 * 如果指定则在添加元素过10个后 使用扩容因子 一次性将容量扩充至原有的1.5倍 新容量 = 原有容量 + 原有容量右移1位
	 */
	private static void testInit() {
		List<String> listZeroCapacity = new ArrayList<>( 0 );
		for ( int i = 0 ; i < 12 ; i++ ) {
			listZeroCapacity.add( i + "" );
		}

		List<String> listTenCapacity = new ArrayList<>();
		for ( int i = 0 ; i < 12 ; i++ ) {
			listTenCapacity.add( i + "" );
		}
	}

	/**
	 * 测试clone方法深拷贝还是浅拷贝
	 * 结论
	 * 仅返回一个ArrayList的影子拷贝即返回一个新的引用
	 * (其中的元素并没有被拷贝,元素该是啥引用还是啥引用,原来指向哪个对象现在还是指向哪个对象,修改新list中的对象,原有list中的对象也会改变)
	 */
	private static void testClone() {
		ArrayList<User> users = new ArrayList<>();
		String namePrefix = "Chung:";
		for ( int i = 0 ; i < 10 ; i++ ) {
			users.add( new User()
					.setId( Integer.toUnsignedLong( i + 1 ) )
					.setName( namePrefix + Integer.toString( i ) )
					.setScore( new Random().nextDouble() )
					.setStatus( User.STATUS_NORMAL )
			);
		}
		users.forEach( System.out::println );
		ArrayList<User> usersClone = (ArrayList<User>) users.clone();
		usersClone.forEach( u -> {
			u.setName( u.getName() + ":Harbor" );
		} );
		System.out.println( "------After change new clone cpylist------" );
		System.out.println( "------Origin list value is------" );
		users.forEach( System.out::println );
	}

	/**
	 * 观察toArray方法
	 * 并修改转换后的array 观察原有list是否更改
	 * <p>
	 * 结论: 修改返回后的数组中的元素对象,原有list中元素不变
	 * 在toArray[]方法调用时
	 * <p>
	 * 若 a.length >= list.size，则将 list 中的元素按顺序存入 a 中，
	 * 然后 a[list.size] = null, a[list.size + 1] 及其后的元素依旧是 a数组 的元素
	 * 否则，将返回包含list 所有元素且数组长度等于 list 中元素个数的数组
	 * 注意：若 a 中本来存储有元素，则 a 会被 list 的元素覆盖，且 a[list.size] = null
	 */
	private static void testToArray() {
		ArrayList<User> users = new ArrayList<>();
		String namePrefix = "Chung:";
		for ( int i = 0 ; i < 10 ; i++ ) {
			users.add( new User()
					.setId( Integer.toUnsignedLong( i + 1 ) )
					.setName( namePrefix + Integer.toString( i ) )
					.setScore( new Random().nextDouble() )
					.setStatus( User.STATUS_NORMAL )
			);
		}
		User[] usersArr = new User[20];
		//init with user
		for ( int i = 0 ; i < usersArr.length ; i++ ) {
			usersArr[i] = new User();
		}
		users.toArray( usersArr );
		System.out.println( "usersArr length is:" + usersArr.length );
		for ( User anUsersArr : usersArr ) {
			System.out.println( anUsersArr );
		}

		for ( User u : usersArr ) {
			if ( u != null ) {
				u.setName( u.getName() + ":Harbor" );
			}
		}
		System.out.println( "------After change cpy array element------" );
		System.out.println( "------Origin list value is------" );
		users.forEach( System.out::println );
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
