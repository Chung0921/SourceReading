package com.chung.source.reading.collections.source.list;

//package java.util;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import java.util.*;

/**
 * Created by Chung.
 * Usage:
 * Description:
 * Create dateTime: 17/5/15
 */
public class ArrayList<E> extends AbstractList<E>
		implements List<E>, RandomAccess, Cloneable, java.io.Serializable {


	/**
	 * Resizable-array implementation of the <tt>List</tt> interface.
	 * list 接口的可调整大小的数组实现。
	 * Implements all optional list operations, and permits all elements, including <tt>null</tt>.
	 * 实现所有可选的列表操作, 并允许所有元素, 包括null元素的操作
	 * In addition to implementing the <tt>List</tt> interface,
	 * this class provides methods to manipulate the size of the array that is used internally to store the list.
	 * 除了实现List接口之外,本类还提供方法来操作在内部用于存储列表的数组的大小。
	 * (This class is roughly equivalent to <tt>Vector</tt>, except that it is unsynchronized.)
	 * 此类大致相当于Vector, 但是它不是同步的。
	 * <p>
	 * <p>The <tt>size</tt>, <tt>isEmpty</tt>, <tt>get</tt>, <tt>set</tt>,
	 * <tt>iterator</tt>, and <tt>listIterator</tt> operations run in constant time.
	 * <p>The <tt>size</tt>, <tt>isEmpty</tt>, <tt>get</tt>, <tt>set</tt>, <tt>iterator</tt>, and <tt>listIterator</tt> 操作在恒定的时间运行。
	 * <p>
	 * The <tt>add</tt> operation runs in <i>amortized constant time</i>, that is, adding n elements requires O(n) time.
	 * add操作 添加n个元素的时间复杂度为O(n).
	 * All of the other operations run in linear time (roughly speaking).
	 * 所有其他操作都在线性时间运行 (粗略地说)。
	 * The constant factor is low compared to that for the <tt>LinkedList</tt> implementation.
	 * 与 <tt> LinkedList </tt> 实现相比, 本类具有的常量元素较低.
	 * <p>
	 * <p>Each <tt>ArrayList</tt> instance has a <i>capacity</i>.
	 * 每一个ArrayList实例均含有一个capacity变量.
	 * The capacity is the size of the array used to store the elements in the list.
	 * capacity变量指定了list中用于存储元素数组的大小.
	 * It is always at least as large as the list size.
	 * 它总是至少与列表大小一样大。
	 * As elements are added to an ArrayList, its capacity grows automatically.
	 * 当元素被添加到ArrayList中时,capacity将会自动增长.
	 * The details of the growth policy are not specified beyond the fact that adding an element has constant amortized time cost.
	 * 事实上,具体的增长策略并没有算在添加元素的时间内.
	 * <p>
	 * <p>An application can increase the capacity of an <tt>ArrayList</tt> instance
	 * before adding a large number of elements using the <tt>ensureCapacity</tt>
	 * operation.  This may reduce the amount of incremental reallocation.
	 * 应用程序可以在添加大量元素之前通过指定ensureCapacity属性的值来提高arraylist实例的容量.
	 * 这将会减少增量重新分配的数量。
	 * <p>
	 * <p><strong>Note that this implementation is not synchronized.</strong>
	 * 注意!本类并不是同步的
	 * If multiple threads access an <tt>ArrayList</tt> instance concurrently,
	 * and at least one of the threads modifies the list structurally, it
	 * <i>must</i> be synchronized externally.
	 * 如果多线程并发的访问一个ArrayList实例,那么至少有一个线程在结构上修改列表,它必须从外部被同步.
	 * <p>
	 * (A structural modification is any operation that adds or deletes one or more elements, or explicitly
	 * resizes the backing array; merely setting the value of an element is not
	 * a structural modification.)  This is typically accomplished by
	 * synchronizing on some object that naturally encapsulates the list.
	 * (结构修改指的是添加或删除一个或多个元素的操作, 或者显式调整后备数组的大小;
	 * 仅设置元素的值不是结构修改。
	 * 这通常通过在自然封装列表的某些对象上进行同步来完成。
	 * <p>
	 * If no such object exists, the list should be "wrapped" using the
	 * {@link Collections#synchronizedList Collections.synchronizedList}
	 * method.
	 * 如果没有对象存在,则list列表应使用 Collections.synchronizedList进行包装.
	 * <p>
	 * This is best done at creation time, to prevent accidental
	 * unsynchronized access to the list:<pre>
	 *   List list = Collections.synchronizedList(new ArrayList(...));</pre>
	 * <p>
	 * <p><a name="fail-fast">
	 * 最好实在创建的时候将list声明为同步的,这样可以最大限度的避免非同步方法的访问
	 * <p>
	 * <p>
	 * The iterators returned by this class's {@link #iterator() iterator} and
	 * {@link #listIterator(int) listIterator} methods are <em>fail-fast</em>:</a>
	 * 使用iterator()方法或listIterator(int)方法将返回本类的迭代器,上述方法均是 "快速-故障"的:
	 * if the list is structurally modified at any time after the iterator is
	 * created, in any way except through the iterator's own
	 * 不论何时,只要list在迭代器被创建之后结构被修改过,那么迭代器将会抛出以下异常
	 * <p>
	 * {@link ListIterator#remove() remove} or
	 * {@link ListIterator#add(Object) add} methods, the iterator will throw a
	 * {@link ConcurrentModificationException}.
	 * <p>
	 * Thus, in the face of
	 * concurrent modification, the iterator fails quickly and cleanly, rather
	 * than risking arbitrary, non-deterministic behavior at an undetermined
	 * time in the future.
	 * 因此,面对并发的修改,迭代器将会迅速和干净地失败, 而不是冒着武断的冒险, 避免不确定的行为在一个不确定的时间在未来发生。
	 * <p>
	 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
	 * as it is, generally speaking, impossible to make any hard guarantees in the
	 * presence of unsynchronized concurrent modification.
	 * 请注意, 迭代器的失效快速行为是不确定的, 因为它通常对于并发条件下的同步问题不做出任何硬性保证。
	 * <p>
	 * Fail-fast iterators
	 * throw {@code ConcurrentModificationException} on a best-effort basis.
	 * 快速失效迭代器在 best-effort 基础上 抛出ConcurrentModificationException异常.
	 * <p>
	 * Therefore, it would be wrong to write a program that depended on this
	 * exception for its correctness:  <i>the fail-fast behavior of iterators
	 * should be used only to detect bugs.</i>
	 * 因此,编写一个依赖于此异常的程序的正确性是错误的:
	 * 迭代器的失效快速行为应仅用于检测 bug
	 * <p>
	 * <p>
	 * <p>This class is a member of the
	 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
	 * Java Collections Framework</a>.
	 *
	 * @author Josh Bloch
	 * @author Neal Gafter
	 * @see Collection
	 * @see List
	 * @see LinkedList
	 * @see Vector
	 * @since 1.2
	 */

//	public class ArrayList<E> extends AbstractList<E>
//			implements List<E>, RandomAccess, Cloneable, java.io.Serializable
//	{
	private static final long serialVersionUID = 8683452581122892189L;

	/**
	 * Default initial capacity.
	 * 默认的初始化大小
	 */
	private static final int DEFAULT_CAPACITY = 10;

	/**
	 * Shared empty array instance used for empty instances.
	 * 用于空实例的共享空数组实例.
	 * elementData存储ArrayList内的元素，size表示它包含的元素的数量.
	 * 当用户指定该 ArrayList 容量为 0 时，返回该空数组
	 * com/chung/source/reading/collections/ArrayList.java:196
	 */
	private static final Object[] EMPTY_ELEMENTDATA = {};

	//如果初始化时指定list长度为0 那么如果向其添加元素时 开始1.5倍扩容 size变化:1,2,3,6,9,13
	//如果指定则在添加元素过10个后 使用扩容因子 一次性将容量扩充至原有的1.5倍 新容量 = 原有容量 + 原有容量右移1位

	/**
	 * Shared empty array instance used for default sized empty instances.
	 * 用于默认大小的空实例的共享空数组实例。
	 * We distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when first element is added.
	 * 我们通过 EMPTY_ELEMENTDATA和DEFAULTCAPACITY_EMPTY_ELEMENTDATA 比对出当第一个元素添加时,膨胀因子应该是多少.
	 * <p>
	 * 当用户没有指定 ArrayList 的容量时(即调用无参构造函数)，返回的是该数组==>刚创建一个 ArrayList 时，其内数据量为 0。
	 * 当用户第一次添加元素时，该数组将会扩容，变成默认容量为 10(DEFAULT_CAPACITY) 的一个数组===>通过  ensureCapacityInternal() 实现
	 * 它与 EMPTY_ELEMENTDATA 的区别就是：该数组是默认返回的，而后者是在用户指定容量为 0 时返回
	 * com/chung/source/reading/collections/ArrayList.java:207
	 */
	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

	/**
	 * The array buffer into which the elements of the ArrayList are stored.
	 * 此为实际存放ArrayList中元素的数组
	 * The capacity of the ArrayList is the length of this array buffer.
	 * capacity属性指定该数组的长度.
	 * Any empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA will be expanded to DEFAULT_CAPACITY when the first element is added.
	 * 当添加第一个元素时,该elementData值为 DEFAULTCAPACITY_EMPTY_ELEMENTDATA 时，当第一次添加元素进入 ArrayList 中时，数组将扩容值 DEFAULT_CAPACITY(10)
	 */
	transient Object[] elementData; // non-private to simplify nested class access

	/**
	 * The size of the ArrayList (the number of elements it contains).
	 * ArrayList的长度(它包含的元素数量)
	 *
	 * @serial
	 */
	private int size;

	/**
	 * Constructs an empty list with the specified initial capacity.
	 * 指定list元素容量大小的构造器
	 *
	 * @param initialCapacity the initial capacity of the list 初始化list的长度
	 * @throws IllegalArgumentException if the specified initial capacity
	 *                                  is negative 如果该长度不可用则抛出此异常
	 */
	public ArrayList( int initialCapacity ) {
		//初始容量大于0,实例化数组
		if ( initialCapacity > 0 ) {
			this.elementData = new Object[initialCapacity];
		} else if ( initialCapacity == 0 ) {
			// 初始化等于0，将空数组赋给elementData
			this.elementData = EMPTY_ELEMENTDATA;
		} else {
			// 初始容量小于，抛异常
			throw new IllegalArgumentException( "Illegal Capacity: " +
					initialCapacity );
		}
	}

	/**
	 * Constructs an empty list with an initial capacity of ten.
	 * 元素容量大小初始化设置为10的构造器
	 */
	public ArrayList() {
		this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
	}

	/**
	 * Constructs a list containing the elements of the specified
	 * collection, in the order they are returned by the collection's
	 * iterator.
	 * 创建一个依据指定collection的ArrayList,元素顺序与该collection实现类的迭代器返回顺序相同.
	 *
	 * @param c the collection whose elements are to be placed into this list
	 *          需要放入list中的collection集合实例
	 * @throws NullPointerException if the specified collection is null
	 *                              如果指定的collection为null则抛出空指针异常
	 */
	public ArrayList( Collection<? extends E> c ) {
		// 集合转 Object[] 数组
		elementData = c.toArray();
		// 将转换后的 Object[] 长度赋值给当前 ArrayList 的 size，并判断是否为 0
		if ( (size = elementData.length) != 0 ) {
			// c.toArray might (incorrectly) not return Object[] (see 6260652)
			// 这句话意思是：c.toArray 可能不会返回 Object[]，可以查看 java 官方编号为 6260652 的 bug
			if ( elementData.getClass() != Object[].class )
				elementData = Arrays.copyOf( elementData, size, Object[].class );
		} else {
			// replace with empty array.
			this.elementData = EMPTY_ELEMENTDATA;
		}
	}

	/**
	 * Trims the capacity of this <tt>ArrayList</tt> instance to be the
	 * list's current size.  An application can use this operation to minimize
	 * the storage of an <tt>ArrayList</tt> instance.
	 * 将ArrayList的数组缓冲区大小调整到ArrayList存储元素的大小,当且仅当list元素长度大于0时,Arrays.copyOf(elementData, size)
	 * 本操作将尽可能的减少ArrayList实例的占用空间的大小.
	 */
	public void trimToSize() {
		//modCount 是 AbstractList 的属性值：protected transient int modCount = 0;
		//记录了该ArrayList涉及结构变化的次数(每调用一次 则modCount++) 涉及的方法有:add()、remove()、addAll()、removeRange()及clear()方法
		//在使用迭代器遍历的时候，用来检查列表中的元素是否发生结构性变化（列表元素数量发生改变）了，主要在多线程环境下需要使用，防止一个线程正在迭代遍历，另一个线程修改了这个列表的结构。
		modCount++;
		if ( size < elementData.length ) {
			elementData = (size == 0)
					? EMPTY_ELEMENTDATA
					: Arrays.copyOf( elementData, size );
		}
	}

	/**
	 * Increases the capacity of this <tt>ArrayList</tt> instance, if
	 * necessary, to ensure that it can hold at least the number of elements
	 * specified by the minimum capacity argument.
	 * 指定 ArrayList 的容量 ,如果必要,可以通过minCapacity参数来指定可以存储元素的最小数量.
	 *
	 * @param minCapacity the desired minimum capacity 指定的最小数量
	 */
	public void ensureCapacity( int minCapacity ) {
		// 最小扩充容量，默认是 10
		int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
				// any size if not default element table
				? 0
				// larger than default for default empty table. It's already
				// supposed to be at default size.
				: DEFAULT_CAPACITY;
		//三元表达式判断如果该list不是默认的空list则最小扩充容量赋值为10
		//若用户指定的最小容量 > 最小扩充容量，则以用户指定的为准，否则还是 10
		if ( minCapacity > minExpand ) {
			ensureExplicitCapacity( minCapacity );
		}
	}

	/**
	 * 明确Arralist本次扩容的容量,节省资源分配,在add及addAll及调用扩容方法时起效
	 *
	 * @param minCapacity 指定的最小容量
	 */
	private void ensureCapacityInternal( int minCapacity ) {
		// 若 elementData == {}，则取 minCapacity 为 默认容量和参数 minCapacity 之间的最大值
		if ( elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA ) {
			minCapacity = Math.max( DEFAULT_CAPACITY, minCapacity );
		}

		ensureExplicitCapacity( minCapacity );
	}

	/**
	 * 明确Arralist本次扩容的容量,节省资源分配,在add及addAll及调用扩容方法时起效
	 *
	 * @param minCapacity 指定的最小容量
	 */
	private void ensureExplicitCapacity( int minCapacity ) {
		// 将“修改统计数”+1，该变量主要是用来实现fail-fast机制的
		modCount++;

		// overflow-conscious code
		// 防止数组溢出: 确保指定的最小容量 > 数组缓冲区当前的长度
		if ( minCapacity - elementData.length > 0 )
			grow( minCapacity );
	}

	/**
	 * The maximum size of array to allocate.
	 * 数组缓冲区最大存储容量
	 * Some VMs reserve some header words in an array.
	 * Attempts to allocate larger arrays may result in
	 * OutOfMemoryError: Requested array size exceeds VM limit
	 * 减8的原因为: 一些 VM 会在一个数组中存储某些数据
	 * 尝试分配对比该数量大的数组容量可能会抛出内存溢出异常
	 */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	/**
	 * Increases the capacity to ensure that it can hold at least the
	 * number of elements specified by the minimum capacity argument.
	 * <p>
	 * 扩容以保证ArrayList可以至少存储minCapacity个元素
	 * 一次性将容量扩充至原有的1.5倍 新容量 = 原有容量 + 原有容量右移1位
	 *
	 * @param minCapacity the desired minimum capacity 最小的容量
	 */
	private void grow( int minCapacity ) {
		// overflow-conscious code
		// 防止溢出代码
		int oldCapacity = elementData.length;
		// 计算新容量的公式
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		// 若计算出的新容量依据比最小容量小 则按最小容量扩容
		if ( newCapacity - minCapacity < 0 )
			newCapacity = minCapacity;
		// 若计算出的新容量比MAX_ARRAY_SIZE还大 则调用hugeCapacity()方法
		if ( newCapacity - MAX_ARRAY_SIZE > 0 )
			//限制扩容的最大值 防止内存溢出
			newCapacity = hugeCapacity( minCapacity );
		// minCapacity is usually close to size, so this is a win:
		elementData = Arrays.copyOf( elementData, newCapacity );
	}

	private static int hugeCapacity( int minCapacity ) {
		if ( minCapacity < 0 ) // overflow
			throw new OutOfMemoryError();
		// 如果最小容量大于MAX_ARRAY_SIZE 则将最大容量分配为Integer.MAX_VALUE
		// 否则容量将为MAX_ARRAY_SIZE
		return (minCapacity > MAX_ARRAY_SIZE) ?
				Integer.MAX_VALUE :
				MAX_ARRAY_SIZE;
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list
	 * <p>
	 * 获取该 list 所实际存储的元素个数
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns <tt>true</tt> if this list contains no elements.
	 *
	 * @return <tt>true</tt> if this list contains no elements
	 * <p>
	 * 判断 list 是否为空
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns <tt>true</tt> if this list contains the specified element.
	 * More formally, returns <tt>true</tt> if and only if this list contains
	 * at least one element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 * 如果返回真则list包含指定元素
	 * 更正式的说,当且仅当list包含至少一个指定元素时,返回true
	 *
	 * @param o element whose presence in this list is to be tested 需要被检测是否包含在list列表中的元素
	 * @return <tt>true</tt> if this list contains the specified element 是否至少包含一个
	 */
	public boolean contains( Object o ) {
		// 根据 indexOf() 的值(索引值)来判断，大于等于 0 就包含
		// 注意：等于 0 的情况不能漏，因为索引号是从 0 开始计数的
		return indexOf( o ) >= 0;
	}

	/**
	 * Returns the index of the first occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 * 返回元素在list中第一次出现的index,如果返回-1则list并不包含该元素
	 * More formally, returns the lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 * 更严谨的说,返回该元素在list中出现下标中最低的一个
	 */
	public int indexOf( Object o ) {
		if ( o == null ) {
			for ( int i = 0 ; i < size ; i++ )
				if ( elementData[i] == null )
					return i;
		} else {
			for ( int i = 0 ; i < size ; i++ )
				if ( o.equals( elementData[i] ) )
					return i;
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 * More formally, returns the highest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 * 逆向查找,元素在list中最后一次出现的位置 返回该下标
	 * 严谨的说,返回list中该元素出现的最高下标
	 * 如果等于-1则不存在该元素
	 */
	public int lastIndexOf( Object o ) {
		if ( o == null ) {
			for ( int i = size - 1 ; i >= 0 ; i-- )
				if ( elementData[i] == null )
					return i;
		} else {
			for ( int i = size - 1 ; i >= 0 ; i-- )
				if ( o.equals( elementData[i] ) )
					return i;
		}
		return -1;
	}

	/**
	 * Returns a shallow copy of this <tt>ArrayList</tt> instance.  (The
	 * elements themselves are not copied.)
	 * 返回一个ArrayList的影子拷贝即返回一个新的引用
	 * (其中的元素并没有被拷贝,元素该是啥引用还是啥引用,原来指向哪个对象现在还是指向哪个对象,修改新list中的对象,原有list中的对象也会改变)
	 * <p>
	 * 浅复制：对拷贝出来的 ArrayList 对象的操作，会影响原来的 ArrayList 仅仅是多了一个list引用而已
	 *
	 * @return a clone of this <tt>ArrayList</tt> instance
	 */
	public Object clone() {
		try {
//			ArrayList<?> v = (ArrayList<?>) super.clone();  //源码
			ArrayList<?> v = (ArrayList<?>) super.clone();                      //本行为自定义代码,避免编译器报错
			// Object 的克隆方法：会复制本对象及其内所有基本类型成员和 String 类型成员，但不会复制对象成员、引用对象
			// 对需要进行复制的引用变量，进行独立的拷贝：将存储的元素移入新的 ArrayList 中
			v.elementData = Arrays.copyOf( elementData, size );
			v.modCount = 0;
			return v;
		} catch ( CloneNotSupportedException e ) {
			// this shouldn't happen, since we are Cloneable
			throw new InternalError( e );
		}
	}

	/**
	 * Returns an array containing all of the elements in this list
	 * in proper sequence (from first to last element).
	 * <p>
	 * 返回一个包含list中所有元素的数组(顺序一致,从第一个到最后一个元素)
	 * <p>
	 * <p>The returned array will be "safe" in that no references to it are
	 * maintained by this list.  (In other words, this method must allocate
	 * a new array).  The caller is thus free to modify the returned array.
	 * <p>
	 * 返回数组中的对象引用是安全的,即修改数组中的对象引用或修改数组本身对原有list不会发生改变
	 * <p>This method acts as bridge between array-based and collection-based
	 * APIs.
	 *
	 * @return an array containing all of the elements in this list in
	 * proper sequence 返回与list中元素存储顺序一致的数组
	 */
	public Object[] toArray() {
		return Arrays.copyOf( elementData, size );
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element);
	 * 返回一个包含list中所有元素的数组(顺序一致,从第一个到最后一个元素)
	 * the runtime type of the returned array is that of the specified array.
	 * 返回指定的特殊的数组类型
	 * If the list fits in the specified array, it is returned therein.
	 * 如果列表符合指定的数组，则返回。
	 * Otherwise, a new array is allocated with the runtime type of the
	 * specified array and the size of this list.
	 * 否则，将用指定数组的运行时类型和该列表的大小分配新数组。
	 * <p>
	 * <p>If the list fits in the specified array with room to spare
	 * (i.e., the array has more elements than the list), the element in
	 * the array immediately following the end of the collection is set to
	 * <tt>null</tt>.  (This is useful in determining the length of the
	 * list <i>only</i> if the caller knows that the list does not contain
	 * any null elements.)
	 * 若 a.length >= list.size，则将 list 中的元素按顺序存入 a 中，
	 * 然后 a[list.size] = null, a[list.size + 1] 及其后的元素依旧是 a数组 的元素
	 * 否则，将返回包含list 所有元素且数组长度等于 list 中元素个数的数组
	 * 注意：若 a 中本来存储有元素，则 a 会被 list 的元素覆盖，且 a[list.size] = null
	 *
	 * @param a the array into which the elements of the list are to
	 *          be stored, if it is big enough;
	 *          如果数组的长度够大,以至于可以存储list中的所有元素
	 *          otherwise, a new array of the
	 *          same runtime type is allocated for this purpose.
	 *          如果长度不够,则将会分配一个新的数组
	 * @return an array containing the elements of the list 返回一个包含list中元素的数组
	 * @throws ArrayStoreException  if the runtime type of the specified array
	 *                              is not a supertype of the runtime type of every element in
	 *                              this list
	 *                              如果指定数组不是list中每个元素的父类 那么将会抛出此异常
	 * @throws NullPointerException if the specified array is null 如果参数中传入的数组是null则抛出空指针异常
	 */
	@SuppressWarnings("unchecked")
	public <T> T[] toArray( T[] a ) {
		if ( a.length < size )
			// Make a new array of a's runtime type, but my contents:
			//如果数组长度小于list的长度 则新建一个数组,指定长度为list中元素的长度后,将list中元素拷贝至array中
			return (T[]) Arrays.copyOf( elementData, size, a.getClass() );
		//如果数组长度大于等于list的长度 则list中元素拷贝至array中
		System.arraycopy( elementData, 0, a, 0, size );
		//如果数组长度大于list的size则将数组中index为size的元素置为null
		if ( a.length > size )
			a[size] = null;
		return a;
	}

	// Positional Access Operations 依据在list中的位置访问元素

	/**
	 * 返回在索引为 index 的元素：数组的随机访问
	 *
	 * @param index 下标
	 * @return 元素
	 */
	@SuppressWarnings("unchecked")
	E elementData( int index ) {
		return (E) elementData[index];
	}

	/**
	 * Returns the element at the specified position in this list.
	 * 获取指定位置的元素：从 0 开始计数
	 *
	 * @param index index of the element to return 元素索引
	 * @return the element at the specified position in this list 返回特定位置的元素
	 * @throws IndexOutOfBoundsException {@inheritDoc} 抛出数组越界异常
	 */
	public E get( int index ) {
		// 检查是否越界
		rangeCheck( index );
		// 返回在索引为 index 的元素
		return elementData( index );
	}

	/**
	 * Replaces the element at the specified position in this list with
	 * the specified element.
	 * 代替list列表中特定位置的元素
	 *
	 * @param index   index of the element to replace  需要被替换的元素索引位置
	 * @param element element to be stored at the specified position 替换后的元素
	 * @return the element previously at the specified position  返回该位置替换前存放的元素
	 * @throws IndexOutOfBoundsException {@inheritDoc} 可能抛出数组越界异常
	 */
	public E set( int index, E element ) {
		// 检查是否越界
		rangeCheck( index );
		// 获取该位置原有元素
		E oldValue = elementData( index );
		// 将新元素放入位置
		elementData[index] = element;
		// 返回原有元素
		return oldValue;
	}

	/**
	 * Appends the specified element to the end of this list.
	 * 向list列表末尾元素后面追加一个新元素
	 *
	 * @param e element to be appended to this list 需要被追加的元素
	 * @return <tt>true</tt> (as specified by {@link Collection#add}) 如果成功添加则返回true
	 */
	public boolean add( E e ) {
		// 列表空间增加1 方法中modCount++
		ensureCapacityInternal( size + 1 );  // Increments modCount!!
		// 放入元素到该位置
		elementData[size++] = e;
		return true;
	}

	/**
	 * Inserts the specified element at the specified position in this
	 * list.
	 * 在指定位置添加元素
	 * <p>
	 * Shifts the element currently at that position (if any) and
	 * any subsequent elements to the right (adds one to their indices).
	 * 从原有元素起,依次向后移动一位
	 *
	 * @param index   index at which the specified element is to be inserted 要插入的位置
	 * @param element element to be inserted 要插入的元素  可能抛出数组越界异常
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 */
	public void add( int index, E element ) {
		//确保index的合法性 需要大于>0且小于list长度
		rangeCheckForAdd( index );

		// 列表空间增加1 方法中modCount++
		ensureCapacityInternal( size + 1 );  // Increments modCount!!
		// 从index开始的元素起赋值到 index+1位置 需要复制个数为size-起始位置
		System.arraycopy( elementData, index, elementData, index + 1,
				size - index );
		// 放入元素到该位置
		elementData[index] = element;
		size++;
	}

	/**
	 * Removes the element at the specified position in this list.
	 * 从list特定位置上移除元素
	 * Shifts any subsequent elements to the left (subtracts one from their
	 * indices).
	 * index之后的元素依次向前移动一位
	 *
	 * @param index the index of the element to be removed 需要移除元素的位置
	 * @return the element that was removed from the list  返回被移除的元素
	 * @throws IndexOutOfBoundsException {@inheritDoc}  可能抛出数组越界异常
	 */
	public E remove( int index ) {
		// 越界检查
		rangeCheck( index );
		// 将“修改统计数”+1，该变量主要是用来实现fail-fast机制的
		modCount++;
		// 取回该位置原有元素
		E oldValue = elementData( index );
		// 获取需要移动元素的数量
		int numMoved = size - index - 1;
		if ( numMoved > 0 )
			System.arraycopy( elementData, index + 1, elementData, index,
					numMoved );
		// 将最后一个元素置null 触发gc回收结论 使用java.lang.System#arraycopy(java.lang.Object, int, java.lang.Object, int, int) 时
		// 仅对区间范围内的元素进行处理,并不处理区间之外的原有元素 所以需要手动赋值为null才会引发gc进行原有对象的回收
		elementData[--size] = null; // clear to let GC do its work

		return oldValue;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if it is present.
	 * 从list列表中从该元素出现的第一次位置删除该元素.
	 * <p>
	 * If the list does not contain the element, it is unchanged.
	 * 如果list并不包含该元素,则list不会改变.
	 * <p>
	 * More formally, removes the element with the lowest index
	 * <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
	 * (if such an element exists).
	 * 更严谨的说,遍历list寻找存在的相同对象,执行删除操作
	 * Returns <tt>true</tt> if this list
	 * contained the specified element (or equivalently, if this list
	 * changed as a result of the call).
	 * 当且仅当,list包含该元素且被删除时返回true
	 * 否则如果不包含该元素 则返回false
	 *
	 * @param o element to be removed from this list, if present 要删除的元素
	 * @return <tt>true</tt> if this list contained the specified element 删除成功返回true,不包含该元素则删除失败
	 */
	public boolean remove( Object o ) {
		if ( o == null ) {
			for ( int index = 0 ; index < size ; index++ )
				if ( elementData[index] == null ) {
					fastRemove( index );
					return true;
				}
		} else {
			for ( int index = 0 ; index < size ; index++ )
				if ( o.equals( elementData[index] ) ) {
					fastRemove( index );
					return true;
				}
		}
		return false;
	}

	/*
	 * Private remove method that skips bounds checking and does not
	 * return the value removed.
	 * 跳过数组越界检查 仅会删除该索引对应的元素 不会返回删除元素
	 */
	private void fastRemove( int index ) {
		// 将“修改统计数”+1，该变量主要是用来实现fail-fast机制的
		modCount++;
		int numMoved = size - index - 1;
		if ( numMoved > 0 )
			System.arraycopy( elementData, index + 1, elementData, index,
					numMoved );
		elementData[--size] = null; // clear to let GC do its work
	}

	/**
	 * Removes all of the elements from this list.  The list will
	 * be empty after this call returns.
	 * 删除list中的所有元素,在本次调用后,返回的list为空.
	 * 清空后，我们直接打印 list，却只会看见一个 [], 而不是 [null, null, ....] ==> toString() 和 迭代器进行了处理
	 */
	public void clear() {
		// 将“修改统计数”+1，该变量主要是用来实现fail-fast机制的
		modCount++;

		// clear to let GC do its work
		//将该list种存放元素对象的缓存数组遍历,每个元素置null,触发gc回收原有元素对象
		for ( int i = 0 ; i < size ; i++ )
			elementData[i] = null;

		size = 0;
	}

	/**
	 * Appends all of the elements in the specified collection to the end of
	 * this list, in the order that they are returned by the
	 * specified collection's Iterator.
	 * 将形参传入的集合中所有的元素追加到list的末尾,顺序与传入的collection实现中的元素顺序一致.
	 * <p>
	 * The behavior of this operation is
	 * undefined if the specified collection is modified while the operation
	 * is in progress.
	 * 如果传入的collection在该操作执行时被更改,那么结果是不确定的(多线程情况,一个在添加该集合,另一个在修改被添加的集合)
	 * <p>
	 * (This implies that the behavior of this call is
	 * undefined if the specified collection is this list, and this
	 * list is nonempty.)
	 *
	 * @param c collection containing elements to be added to this list 需要添加到list中的元素集合
	 * @return <tt>true</tt> if this list changed as a result of the call 如果list的元素个数有改变时,返回true否则失败返回false
	 * @throws NullPointerException if the specified collection is null 如果c为null时抛出空指针异常
	 */
	public boolean addAll( Collection<? extends E> c ) {
		//若c为null 则调用toArray时将抛出空指针
		Object[] a = c.toArray();
		//新元素的长度为传入集合的长度
		int numNew = a.length;
		//扩容
		ensureCapacityInternal( size + numNew );  // Increments modCount
		//将集合中的元素添加到list的缓存数组中
		System.arraycopy( a, 0, elementData, size, numNew );
		//更新list现有长度
		size += numNew;
		//判断新添加的元素个数是否为0
		return numNew != 0;
	}

	/**
	 * Inserts all of the elements in the specified collection into this
	 * list, starting at the specified position.
	 * 从list中的指定位置添加collection中的所有元素
	 * <p>
	 * Shifts the element
	 * currently at that position (if any) and any subsequent elements to
	 * the right (increases their indices).
	 * 将index开始的缘由元素向后移动collection.size个位置
	 * <p>
	 * The new elements will appear
	 * in the list in the order that they are returned by the
	 * specified collection's iterator.
	 * list中新添加的元素顺序,与在collection中时一致
	 *
	 * @param index index at which to insert the first element from the
	 *              specified collection
	 *              collection中第一个元素将插入list中的位置
	 * @param c     collection containing elements to be added to this list 需要添加到list中的元素集合
	 * @return <tt>true</tt> if this list changed as a result of the call 如果list的元素个数有改变时,返回true否则失败返回false
	 * @throws IndexOutOfBoundsException {@inheritDoc}  越界异常
	 * @throws NullPointerException      if the specified collection is null 如果c为null时抛出空指针异常
	 */
	public boolean addAll( int index, Collection<? extends E> c ) {
		//判断index是否越界
		rangeCheckForAdd( index );

		//若c为null 则调用toArray时将抛出空指针
		Object[] a = c.toArray();
		//新元素的长度为传入集合的长度
		int numNew = a.length;
		//扩容
		ensureCapacityInternal( size + numNew );  // Increments modCount
		//需要移动的元素个数
		int numMoved = size - index;
		if ( numMoved > 0 )
			//将原有元素先行移动,再拷贝,防止覆盖
			System.arraycopy( elementData, index, elementData, index + numNew,
					numMoved );
		//将collection中的元素插入到list中的缓存数组中
		System.arraycopy( a, 0, elementData, index, numNew );
		//更新list现有长度
		size += numNew;
		//判断新添加的元素个数是否为0
		return numNew != 0;
	}

	/**
	 * Removes from this list all of the elements whose index is between
	 * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.
	 * <p>
	 * 删除fromIndex到toIndex之间的全部元素 [fromIndex,toIndex)
	 * <p>
	 * Shifts any succeeding elements to the left (reduces their index).
	 * 将toIndex之后的元素,包含toIndex元素,左移补位.
	 * <p>
	 * This call shortens the list by {@code (toIndex - fromIndex)} elements.
	 * list在调用后将会减少(toIndex - fromIndex)个元素.
	 * <p>
	 * (If {@code toIndex==fromIndex}, this operation has no effect.)
	 * 如果toIndex等于fromIndex,则不会发生任何操作.
	 *
	 * @throws IndexOutOfBoundsException if {@code fromIndex} or
	 *                                   {@code toIndex} is out of range
	 *                                   ({@code fromIndex < 0 ||
	 *                                   fromIndex >= size() ||
	 *                                   toIndex > size() ||
	 *                                   toIndex < fromIndex})
	 */
	protected void removeRange( int fromIndex, int toIndex ) {
		// 将“修改统计数”+1，该变量主要是用来实现fail-fast机制的
		modCount++;
		// 计算需要移动元素个数
		int numMoved = size - toIndex;
		// 执行移动元素操作
		System.arraycopy( elementData, toIndex, elementData, fromIndex,
				numMoved );

		// clear to let GC do its work
		// 计算新的长度
		int newSize = size - (toIndex - fromIndex);
		// 置null 触发gc回收这些位置上原有的对象
		for ( int i = newSize ; i < size ; i++ ) {
			elementData[i] = null;
		}
		// 刷新列表长度
		size = newSize;
	}


	/**
	 * Checks if the given index is in range.
	 * 检查index是否在范围内.
	 * <p>
	 * If not, throws an appropriate runtime exception.
	 * 如果不在则抛出一个运行时异常.
	 * <p>
	 * This method does *not* check if the index is negative:
	 * 此方法不检查索引是否为负值:
	 * <p>
	 * It is always used immediately prior to an array access,
	 * which throws an ArrayIndexOutOfBoundsException if index is negative.
	 * 它总是在数组访问之前立即使用,
	 * 如果索引为负数, 则抛出一个 ArrayIndexOutOfBoundsException。
	 */
	private void rangeCheck( int index ) {
		if ( index >= size )
			throw new IndexOutOfBoundsException( outOfBoundsMsg( index ) );
	}

	/**
	 * A version of rangeCheck used by add and addAll.
	 * 在添加元素前进行校验的方法
	 */
	private void rangeCheckForAdd( int index ) {
		if ( index > size || index < 0 )
			throw new IndexOutOfBoundsException( outOfBoundsMsg( index ) );
	}

	/**
	 * Constructs an IndexOutOfBoundsException detail message.
	 * Of the many possible refactorings of the error handling code,
	 * this "outlining" performs best with both server and client VMs.
	 * 返回异常消息概述，用于传给 IndexOutOfBoundsException
	 */
	private String outOfBoundsMsg( int index ) {
		return "Index: " + index + ", Size: " + size;
	}

	/**
	 * Removes from this list all of its elements that are contained in the
	 * specified collection.
	 * 移除在list中和collection中共有的元素
	 *
	 * @param c collection containing elements to be removed from this list
	 *          包含需要移除元素的集合
	 * @return {@code true} if this list changed as a result of the call
	 * 如果集合被改变(个数改变)则返回成功
	 * @throws ClassCastException   if the class of an element of this list
	 *                              is incompatible with the specified collection
	 *                              如果类型不匹配
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException if this list contains a null element and the
	 *                              specified collection does not permit null elements
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>),
	 *                              or if the specified collection is null
	 *                              如果list包含null且collection不可包含null元素或者collection是null则抛空指针
	 * @see Collection#contains(Object)
	 */
	public boolean removeAll( Collection<?> c ) {
		//如果c为null则抛空指针
		Objects.requireNonNull( c );
		return batchRemove( c, false );
	}

	/**
	 * Retains only the elements in this list that are contained in the
	 * specified collection.  In other words, removes from this list all
	 * of its elements that are not contained in the specified collection.
	 * 只保留list与collection中共有的元素.
	 *
	 * @param c collection containing elements to be retained in this list
	 *          包含需要移除元素的集合
	 * @return {@code true} if this list changed as a result of the call
	 * 如果集合被改变(个数改变)则返回成功
	 * @throws ClassCastException   if the class of an element of this list
	 *                              is incompatible with the specified collection
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 *                              如果类型不匹配
	 * @throws NullPointerException if this list contains a null element and the
	 *                              specified collection does not permit null elements
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>),
	 *                              or if the specified collection is null
	 *                              如果list包含null且collection不可包含null元素或者collection是null则抛空指针
	 * @see Collection#contains(Object)
	 */
	public boolean retainAll( Collection<?> c ) {
		Objects.requireNonNull( c );
		return batchRemove( c, true );
	}

	/**
	 * 批量删除元素
	 *
	 * @param c          包含待移除元素的集合
	 * @param complement 是否取补集
	 * @return 是否移除成功
	 */
	private boolean batchRemove( Collection<?> c, boolean complement ) {
		/**
		 * 移除调用batchRemove流程:
		 * 测试移除的元素集合如下: 包含元素4,5,6,7,8
		 * 原有集合的元素如下: 包含元素1,2,3,4,5,6,7,8,9,10
		 * complement = false;
		 * 循环判断 待移除的集合中是否包含原有集合的元素 如果不包含 则将该元素放入数组w++位置(从0开始)
		 * 第一个循环结束时,ArrayList中缓存元素数组变为1,2,3,9,10 ,6,7,8,9,10
		 * w=5,r=10
		 * 判断结果 r不等于size if中代码块跳过
		 * 判断结果 w等于size
		 * 从w位置开始将原有缓存数组中的元素置null,触发gc回收
		 * fail-fast变量计算次数(添加本次置null的元素个数)
		 * ArrayList刷新size值为w
		 * 个数修改遍历置true后返回
		 */

		/**
		 * 保留方法 调用batchRemove流程:
		 * 测试移除的元素集合如下: 包含元素4,5,6,7,8
		 * 原有集合的元素如下: 包含元素1,2,3,4,5,6,7,8,9,10
		 * complement = true;
		 * 循环判断 保留的集合中是否包含原有集合的元素 如果包含 则将该元素放入数组w++位置(从0开始)
		 * 第一个循环结束时,ArrayList中缓存元素数组变为4,5,6,7,8, 6,7,8,9,10
		 * w=5,r=10
		 * 判断结果 r不等于size if中代码块跳过
		 * 判断结果 w等于size
		 * 从w位置开始将原有缓存数组中的元素置null,触发gc回收
		 * fail-fast变量计算次数(添加本次置null的元素个数)
		 * ArrayList刷新size值为w
		 * 个数修改遍历置true后返回
		 */
		//原有元素集合
		final Object[] elementData = this.elementData;
		//读指针 写指针
		int r = 0, w = 0;
		//是否修改完成
		boolean modified = false;
		try {
			//遍历原有list元素
			for ( ; r < size ; r++ )
				if ( c.contains( elementData[r] ) == complement )
					//则将该元素放入w++位置
					elementData[w++] = elementData[r];
		} finally {
			// Preserve behavioral compatibility with AbstractCollection,
			// even if c.contains() throws.
			//如果在本次处理中list发生了改变
			if ( r != size ) {
				// 此步未验证,猜想为:数组中从r开始获取长度为size - r个元素w位置开始重新放入缓冲数组 将新添加入集合的元素放入结果集中再进行下一步处理
				System.arraycopy( elementData, r,
						elementData, w,
						size - r );
				w += size - r;
			}
			//如果w与原有size不相等 则遍历将w位置后的元素置空
			if ( w != size ) {
				// clear to let GC do its work
				for ( int i = w ; i < size ; i++ )
					elementData[i] = null;
				modCount += size - w;
				size = w;
				modified = true;
			}
		}
		return modified;
	}

	/**
	 * Save the state of the <tt>ArrayList</tt> instance to a stream (that
	 * is, serialize it).
	 * 保存当前的ArrayList实例序列化后输出到流到中
	 *
	 * @serialData The length of the array backing the <tt>ArrayList</tt>
	 * instance is emitted (int), followed by all of its elements
	 * (each an <tt>Object</tt>) in the proper order.
	 */
	private void writeObject( java.io.ObjectOutputStream s )
			throws java.io.IOException {
		// Write out element count, and any hidden stuff
		int expectedModCount = modCount;
		s.defaultWriteObject();

		// Write out size as capacity for behavioural compatibility with clone()
		s.writeInt( size );

		// Write out all elements in the proper order.
		for ( int i = 0 ; i < size ; i++ ) {
			s.writeObject( elementData[i] );
		}

		if ( modCount != expectedModCount ) {
			throw new ConcurrentModificationException();
		}
	}

	/**
	 * Reconstitute the <tt>ArrayList</tt> instance from a stream (that is,
	 * deserialize it).
	 * 反序列化流重建ArrayList实例
	 */
	private void readObject( java.io.ObjectInputStream s )
			throws java.io.IOException, ClassNotFoundException {
		elementData = EMPTY_ELEMENTDATA;

		// Read in size, and any hidden stuff
		s.defaultReadObject();

		// Read in capacity
		s.readInt(); // ignored

		if ( size > 0 ) {
			// be like clone(), allocate array based upon size not capacity
			ensureCapacityInternal( size );

			Object[] a = elementData;
			// Read in all elements in the proper order.
			for ( int i = 0 ; i < size ; i++ ) {
				a[i] = s.readObject();
			}
		}
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper
	 * sequence), starting at the specified position in the list.
	 * 从指定的位置开始返回一个后续元素的list迭代器(包含此index对应的元素)
	 * <p>
	 * The specified index indicates the first element that would be
	 * returned by an initial call to {@link ListIterator#next next}.
	 * 通过调用.next()方法返回第一个元素
	 * <p>
	 * An initial call to {@link ListIterator#previous previous} would
	 * return the element with the specified index minus one.
	 * previous()方法调用将会返回之前一个index的元素
	 * <p>
	 * <p>
	 * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
	 *
	 * @throws IndexOutOfBoundsException {@inheritDoc} 数组越界异常
	 */
	public ListIterator<E> listIterator( int index ) {
		//越界合法性校验
		if ( index < 0 || index > size )
			throw new IndexOutOfBoundsException( "Index: " + index );
		return new ArrayList.ListItr( index );
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper
	 * sequence).
	 * 返回一个 ListIterator 迭代器，该迭代器是 fail-fast 机制的
	 * <p>
	 * <p>
	 * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
	 *
	 * @see #listIterator(int)
	 */
	public ListIterator<E> listIterator() {
		return new ArrayList.ListItr( 0 );
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * <p>
	 * <p>The returned iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
	 * <p>
	 * 返回一个 Iterator 迭代器，该迭代器是 fail-fast 机制的
	 *
	 * @return an iterator over the elements in this list in proper sequence
	 */
	public Iterator<E> iterator() {
		return new ArrayList.Itr();
	}

	/**
	 * An optimized version of AbstractList.Itr
	 * <p>
	 * AbstractList.Itr的优化版本
	 */
	private class Itr implements Iterator<E> {
		// 游标 下一个返回元素的索引,默认值为 0
		int cursor;       // index of next element to return
		// 上一次返回元素的索引,若没有上一个返回的元素,则为 -1.每次调用 Itr#remove()及 ListItr#add(),lastRet 都会重置为 -1 .
		int lastRet = -1; // index of last element returned; -1 if no such
		// 支持 fail-fast
		int expectedModCount = modCount;

		// 是否有下一元素的判断
		public boolean hasNext() {
			return cursor != size;
		}

		@SuppressWarnings("unchecked")
		public E next() {
			checkForComodification();
			// 临时变量 i，指向游标当前位置。
			// 此处并没有让 lastRet 直接等于 cursor 进行操作
			int i = cursor;
			if ( i >= size )
				throw new NoSuchElementException();
			Object[] elementData = ArrayList.this.elementData;
			if ( i >= elementData.length )
				throw new ConcurrentModificationException();
			cursor = i + 1;
			return (E) elementData[lastRet = i];
		}

		public void remove() {
			if ( lastRet < 0 )
				throw new IllegalStateException();
			checkForComodification();

			try {
				ArrayList.this.remove( lastRet );
				cursor = lastRet;
				lastRet = -1;
				expectedModCount = modCount;
			} catch ( IndexOutOfBoundsException ex ) {
				throw new ConcurrentModificationException();
			}
		}

		/**
		 * 集合中每个元素均执行此操作
		 *
		 * @param consumer 需要执行的函数
		 */
		//		@Override
		@SuppressWarnings("unchecked")
		public void forEachRemaining( Consumer<? super E> consumer ) {
			Objects.requireNonNull( consumer );
			final int size = ArrayList.this.size;
			int i = cursor;
			if ( i >= size ) {
				return;
			}
			final Object[] elementData = ArrayList.this.elementData;
			if ( i >= elementData.length ) {
				throw new ConcurrentModificationException();
			}
			while ( i != size && modCount == expectedModCount ) {
				consumer.accept( (E) elementData[i++] );
			}
			// update once at end of iteration to reduce heap write traffic
			//指针指向最后一个元素
			cursor = i;
			lastRet = i - 1;
			checkForComodification();
		}

		// fast-fail check
		final void checkForComodification() {
			if ( modCount != expectedModCount )
				throw new ConcurrentModificationException();
		}
	}

	/**
	 * An optimized version of AbstractList.ListItr
	 * AbstractList.ListItr 的优化版本
	 * ListIterator 与普通的 Iterator 的区别：
	 * - 它可以进行双向移动，而普通的迭代器只能单向移动
	 * - 它可以添加元素(有 add() 方法)，而后者不行
	 */
	private class ListItr extends Itr implements ListIterator<E> {
		ListItr( int index ) {
			super();
			cursor = index; // cursor 指向下一个返回元素的索引位置
		}

		/**
		 * 是否有上一个元素
		 *
		 * @return 有返回true
		 */
		public boolean hasPrevious() {
			return cursor != 0;
		}

		/**
		 * 获取下一个元素的索引
		 *
		 * @return 下一元素的索引下标
		 */
		public int nextIndex() {
			return cursor;
		}

		/**
		 * 本方法获取 cursor 前一个元素的索引
		 * - 本方法是获取 cursor 前一个，而不是当前元素前一个的索引。
		 * - 若调用 next() 后马上调用该方法，则返回的是当前元素的索引。
		 * - 若调用 next() 后想获取当前元素前一个元素的索引，需要连续调用两次该方法[先获取到当前元素的索引cursor-1 再获取到当前元素前一个元素的索引cursor-1-1]。
		 */
		public int previousIndex() {
			return cursor - 1;
		}

		/**
		 * 与previousIndex相同 只不过 previousIndex 返回元素索引位置 而本方法返回该索引位置对应的元素
		 *
		 * @return 获取cursor前一个元素
		 */
		@SuppressWarnings("unchecked")
		public E previous() {
			checkForComodification();
			int i = cursor - 1;
			if ( i < 0 )
				throw new NoSuchElementException();
			Object[] elementData = ArrayList.this.elementData;
			if ( i >= elementData.length )
				throw new ConcurrentModificationException();
			//下一个元素游标--
			cursor = i;
			//返回当前元素 且上一个元素指针指向i
			return (E) elementData[lastRet = i];
		}

		/**
		 * 在上一次返回元素的索引位置 放入新元素
		 *
		 * @param e 将要被放入的元素
		 */
		public void set( E e ) {
			if ( lastRet < 0 )
				throw new IllegalStateException();
			checkForComodification();

			try {
				//将元素放入上一次返回的元素索引位置
				ArrayList.this.set( lastRet, e );
			} catch ( IndexOutOfBoundsException ex ) {
				throw new ConcurrentModificationException();
			}
		}

		/**
		 * 向下一个元素游标指向的位置插入一个元素,后续元素均右移1位
		 *
		 * @param e 待插入的元素
		 */
		public void add( E e ) {
			checkForComodification();

			try {
				//获取下一个元素游标
				int i = cursor;
				//执行插入及右移操作
				ArrayList.this.add( i, e );
				//刷新下一个元素游标
				cursor = i + 1;
				//上一个元素位置游标置为-1
				lastRet = -1;
				//刷新fast-fail标量
				expectedModCount = modCount;
			} catch ( IndexOutOfBoundsException ex ) {
				throw new ConcurrentModificationException();
			}
		}
	}

	/**
	 * Returns a view of the portion of this list between the specified
	 * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.
	 * 返回[fromIndex,toIndex)之间的元素集合.
	 * <p>
	 * (If {@code fromIndex} and {@code toIndex} are equal, the returned list is
	 * empty.)
	 * 如果fromIndex与toIndex相等,那么返回一个空集合list.
	 * <p>
	 * The returned list is backed by this list, so non-structural
	 * changes in the returned list are reflected in this list, and vice-versa.
	 * The returned list supports all of the optional list operations.
	 * 返回的子集合是基于原有集合的 所以,非结构化的操作将会影响原有集合.(返回的子集合支持list的所有可选操作.)
	 * <p>
	 * <p>This method eliminates the need for explicit range operations (of the sort that commonly exist for arrays).
	 * 此方法消除了显式范围操作 (对于数组通常存在的排序) 的需要.
	 * <p>
	 * Any operation that expects
	 * a list can be used as a range operation by passing a subList view
	 * instead of a whole list.
	 * 一些希望更改父集合的操作可以通过操作子集从而实现.
	 * <p>
	 * For example, the following idiom
	 * removes a range of elements from a list:
	 * <pre>
	 *      list.subList(from, to).clear();
	 * </pre>
	 * 如清除父集合中一定范围内元素 可使用list.subList(from, to).clear();
	 * <p>
	 * Similar idioms may be constructed for {@link #indexOf(Object)} and
	 * {@link #lastIndexOf(Object)}, and all of the algorithms in the
	 * {@link Collections} class can be applied to a subList.
	 * <p>
	 * 可以为 {@link # indexof (对象)} 构造类似的习语,
	 * {@link # lastIndexOf (对象)} 和所有算法中的
	 * {@link Collections} 类可应用于子列表。
	 * <p>
	 * <p>
	 * <p>The semantics of the list returned by this method become undefined if
	 * the backing list (i.e., this list) is <i>structurally modified</i> in
	 * any way other than via the returned list.
	 * <p>
	 * (Structural modifications are
	 * those that change the size of this list, or otherwise perturb it in such
	 * a fashion that iterations in progress may yield incorrect results.)
	 *
	 * @throws IndexOutOfBoundsException {@inheritDoc}
	 * @throws IllegalArgumentException  {@inheritDoc}
	 */
	public List<E> subList( int fromIndex, int toIndex ) {
		subListRangeCheck( fromIndex, toIndex, size );
		return new ArrayList.SubList( this, 0, fromIndex, toIndex );
	}

	// 合法性检查 防止越界
	static void subListRangeCheck( int fromIndex, int toIndex, int size ) {
		if ( fromIndex < 0 )
			throw new IndexOutOfBoundsException( "fromIndex = " + fromIndex );
		if ( toIndex > size )
			throw new IndexOutOfBoundsException( "toIndex = " + toIndex );
		if ( fromIndex > toIndex )
			throw new IllegalArgumentException( "fromIndex(" + fromIndex +
					") > toIndex(" + toIndex + ")" );
	}

	private class SubList extends AbstractList<E> implements RandomAccess {
		//父集合引用变量
		private final AbstractList<E> parent;
		//父集合偏移 起始元素下标
		private final int parentOffset;
		//子集合偏移量 子集合中的首元素不能删除 因为offset变量已经记录了父集合偏移的起始地址
		private final int offset;
		//子集合大小
		int size;

		//子集合仅仅是对父集合相应区间的引用
		SubList( AbstractList<E> parent,
		         int offset, int fromIndex, int toIndex ) {
			//父集合引用赋值
			this.parent = parent;
			//子集合第一个元素在父集合中的偏移量赋值
			this.parentOffset = fromIndex;
			//计算子集合中的偏移量
			this.offset = offset + fromIndex;
			//计算子集合长度
			this.size = toIndex - fromIndex;
			//fast-fail
			this.modCount = ArrayList.this.modCount;
		}

		//向子集合中特定index下标指定设置新元素 其实是计算出父集合的index然后修改父集合从而影响子集合
		public E set( int index, E e ) {
			//越界检查
			rangeCheck( index );
			//fast-fail
			checkForComodification();
			//获取原有元素
			E oldValue = ArrayList.this.elementData( offset + index );
			//赋值
			ArrayList.this.elementData[offset + index] = e;
			//返回原有元素
			return oldValue;
		}

		//获取子集合中特定index的元素 其实是计算出父集合的index然后从父集合中获取后返回
		public E get( int index ) {
			//越界检查
			rangeCheck( index );
			//fast-fail
			checkForComodification();
			//获取原有元素
			return ArrayList.this.elementData( offset + index );
		}

		//获取子集合大小
		public int size() {
			//fast-fail
			checkForComodification();
			return this.size;
		}

		/**
		 * 向子集合特定index插入元素e
		 * 实际是计算出父集合的index后插入父集合从而影响子集合
		 *
		 * @param index index子集合的
		 * @param e     待添加的元素e
		 */
		public void add( int index, E e ) {
			//越界检查
			rangeCheckForAdd( index );
			//fast-fail
			checkForComodification();
			//向父集合中添加元素e 后续元素右移1位
			parent.add( parentOffset + index, e );
			//fast-fail
//			this.modCount = parent.modCount;
			//子集合数量++
			this.size++;
		}

		/**
		 * 从子集合特定index删除元素
		 * 实际是计算出父集合的index后插入父集合从而影响子集合
		 *
		 * @param index index子集合的
		 */
		public E remove( int index ) {
			//越界检查
			rangeCheck( index );
			//fast-fail
			checkForComodification();
			//向父集合中去除index位置的元素 后续元素右移1位
			E result = parent.remove( parentOffset + index );
			//fast-fail
//			this.modCount = parent.modCount;
			//子集合数量--
			this.size--;
			return result;
		}

		/**
		 * 从子集合中删除特定范围内的元素
		 *
		 * @param fromIndex 起始index
		 * @param toIndex   结束index
		 */
		protected void removeRange( int fromIndex, int toIndex ) {
			//fast-fail
			checkForComodification();
			//计算父集合中的index范围 调用父集合方法执行删除
//			parent.removeRange( parentOffset + fromIndex,
//					parentOffset + toIndex );
			//fast-fail
//			this.modCount = parent.modCount;
			//子集合数量刷新
			this.size -= toIndex - fromIndex;
		}

		//添加集合c中元素
		public boolean addAll( Collection<? extends E> c ) {
			//com.chung.source.reading.collections.ArrayList.SubList.addAll(int, java.util.Collection<? extends E>)
			return addAll( this.size, c );
		}

		/**
		 * 向指定index位置插入集合c中所有元素
		 *
		 * @param index 子集合中的位置,用于计算父集合中的位置 然后执行插入操作
		 * @param c     待插入元素的集合
		 * @return 是否插入成功
		 */
		public boolean addAll( int index, Collection<? extends E> c ) {
			//越界检查
			rangeCheckForAdd( index );
			//判断传入集合是否有值
			int cSize = c.size();
			if ( cSize == 0 )
				return false;

			//fast-fail check
			checkForComodification();
			//调用父集合中addAll方法 传入参数index为根据子集合中index及父集合中偏移而计算好父集合中的index 以及需要添加的元素集合c
			parent.addAll( parentOffset + index, c );
			//fast-fail check
//			this.modCount = parent.modCount;
			//刷新子集合size遍历
			this.size += cSize;
			return true;
		}

		// SubList 的方法：返回一个迭代器，虽说是返回抽象的 Iterator，但具体实现是 ListIterator
		public Iterator<E> iterator() {
			return listIterator();
		}

		// SubList 的方法：返回一个 ListIterator
		public ListIterator<E> listIterator( final int index ) {
			//fast-fail check
			checkForComodification();
			//越界检查
			rangeCheckForAdd( index );
			//父集合中偏移
			final int offset = this.offset;
			//与父集合中的迭代器没啥区别 就是添加了偏移 计算以后才去取
			return new ListIterator<E>() {
				int cursor = index;
				int lastRet = -1;
				int expectedModCount = ArrayList.this.modCount;

				public boolean hasNext() {
					return cursor != ArrayList.SubList.this.size;
				}

				@SuppressWarnings("unchecked")
				public E next() {
					//fast-fail check
					checkForComodification();
					int i = cursor;
					if ( i >= ArrayList.SubList.this.size )
						throw new NoSuchElementException();
					Object[] elementData = ArrayList.this.elementData;
					if ( offset + i >= elementData.length )
						throw new ConcurrentModificationException();
					cursor = i + 1;
					return (E) elementData[offset + (lastRet = i)];
				}

				public boolean hasPrevious() {
					return cursor != 0;
				}

				@SuppressWarnings("unchecked")
				public E previous() {
					checkForComodification();
					int i = cursor - 1;
					if ( i < 0 )
						throw new NoSuchElementException();
					Object[] elementData = ArrayList.this.elementData;
					if ( offset + i >= elementData.length )
						throw new ConcurrentModificationException();
					cursor = i;
					return (E) elementData[offset + (lastRet = i)];
				}

				@SuppressWarnings("unchecked")
				public void forEachRemaining( Consumer<? super E> consumer ) {
					Objects.requireNonNull( consumer );
					final int size = ArrayList.SubList.this.size;
					int i = cursor;
					if ( i >= size ) {
						return;
					}
					final Object[] elementData = ArrayList.this.elementData;
					if ( offset + i >= elementData.length ) {
						throw new ConcurrentModificationException();
					}
					while ( i != size && modCount == expectedModCount ) {
						consumer.accept( (E) elementData[offset + (i++)] );
					}
					// update once at end of iteration to reduce heap write traffic
					lastRet = cursor = i;
					checkForComodification();
				}

				public int nextIndex() {
					return cursor;
				}

				public int previousIndex() {
					return cursor - 1;
				}

				public void remove() {
					if ( lastRet < 0 )
						throw new IllegalStateException();
					checkForComodification();

					try {
						ArrayList.SubList.this.remove( lastRet );
						cursor = lastRet;
						lastRet = -1;
						expectedModCount = ArrayList.this.modCount;
					} catch ( IndexOutOfBoundsException ex ) {
						throw new ConcurrentModificationException();
					}
				}

				public void set( E e ) {
					if ( lastRet < 0 )
						throw new IllegalStateException();
					checkForComodification();

					try {
						ArrayList.this.set( offset + lastRet, e );
					} catch ( IndexOutOfBoundsException ex ) {
						throw new ConcurrentModificationException();
					}
				}

				public void add( E e ) {
					checkForComodification();

					try {
						int i = cursor;
						ArrayList.SubList.this.add( i, e );
						cursor = i + 1;
						lastRet = -1;
						expectedModCount = ArrayList.this.modCount;
					} catch ( IndexOutOfBoundsException ex ) {
						throw new ConcurrentModificationException();
					}
				}

				final void checkForComodification() {
					if ( expectedModCount != ArrayList.this.modCount )
						throw new ConcurrentModificationException();
				}
			};
		}

		//返回子集的子集
		public List<E> subList( int fromIndex, int toIndex ) {
			subListRangeCheck( fromIndex, toIndex, size );
			return new ArrayList.SubList( this, offset, fromIndex, toIndex );
		}

		//越界检查 remove removeAll
		private void rangeCheck( int index ) {
			if ( index < 0 || index >= this.size )
				throw new IndexOutOfBoundsException( outOfBoundsMsg( index ) );
		}

		//越界检查 add addAll
		private void rangeCheckForAdd( int index ) {
			if ( index < 0 || index > this.size )
				throw new IndexOutOfBoundsException( outOfBoundsMsg( index ) );
		}

		// 返回异常消息概述，用于传给 IndexOutOfBoundsException
		private String outOfBoundsMsg( int index ) {
			return "Index: " + index + ", Size: " + this.size;
		}

		//fast-fail check
		private void checkForComodification() {
			if ( ArrayList.this.modCount != this.modCount )
				throw new ConcurrentModificationException();
		}

		//获取分割器方法
		public Spliterator<E> spliterator() {
			checkForComodification();
			return new ArrayList.ArrayListSpliterator<E>( ArrayList.this, offset,
					offset + this.size, this.modCount );
		}
	}

	//遍历list 对于每个元素使用Consumer 每循环一次均检查集合结构是否更改 更改则抛出异常
	@Override
	public void forEach( Consumer<? super E> action ) {
		Objects.requireNonNull( action );
		final int expectedModCount = modCount;
		@SuppressWarnings("unchecked")
		final E[] elementData = (E[]) this.elementData;
		final int size = this.size;
		for ( int i = 0 ; modCount == expectedModCount && i < size ; i++ ) {
			action.accept( elementData[i] );
		}
		if ( modCount != expectedModCount ) {
			throw new ConcurrentModificationException();
		}
	}

	/**
	 * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
	 * and <em>fail-fast</em> {@link Spliterator} over the elements in this
	 * list.
	 * <p>
	 * <p>The {@code Spliterator} reports {@link Spliterator#SIZED},
	 * {@link Spliterator#SUBSIZED}, and {@link Spliterator#ORDERED}.
	 * Overriding implementations should document the reporting of additional
	 * characteristic values.
	 *
	 * @return a {@code Spliterator} over the elements in this list
	 * @since 1.8
	 */
	@Override
	public Spliterator<E> spliterator() {
		return new ArrayList.ArrayListSpliterator<>( this, 0, -1, 0 );
	}

	/**
	 * Index-based split-by-two, lazily initialized Spliterator
	 * 基于索引的、二分的、懒加载的分割器
	 */
	static final class ArrayListSpliterator<E> implements Spliterator<E> {

		/*
		 * If ArrayLists were immutable, or structurally immutable (no adds, removes, etc),
		 * we could implement their spliterators with Arrays.spliterator.
		 * 如果 ArrayLists 是不可变的或结构不变的 (不添加、删除等),我们可以使用数组实现它们的 spliterators.
		 *
		 * Instead we detect as much interference during traversal as practical without sacrificing much performance.
		 * 通过遍历我们将会牺牲掉很多性能,所以
		 *
		 * We rely primarily on modCounts.
		 * 我们主要依靠 modCounts进行检测.
		 *
		 * These are not guaranteed to detect concurrency violations,
		 * and are sometimes overly conservative about within-thread interference, but detect enough problems to be worthwhile in practice.
		 * 这些方法不能保证检测并发冲突, 有时在线程内干扰方面过于保守, 但在实践中可以发现足够多的问题。
		 *
		 * To carry this out, we (1) lazily initialize fence and expectedModCount until the latest
		 * point that we need to commit to the state we are checking against;
		 * 为了解决这个问题, 首先 我们需要对我们正在检查的状态作出承诺 通过使用懒加载方式初始化 fence and expectedModCount 直到最新的一个point
		 *
		 * thus improving precision.
		 * 从而提高精确度。
		 *
		 * (This doesn't apply to SubLists, that create spliterators with current non-lazy values).
		 * 但是这个不适用与SubLists,SubLists使用非懒加载方式初始化变量.
		 *
		 * (2) We perform only a single ConcurrentModificationException check at the end of forEach
		 * 在 foreach 的末尾, 我们只执行一个 ConcurrentModificationException 检查
		 * (the most performance-sensitive method).
		 * (最具性能敏感性的方法)
		 *
		 * When using forEach (as opposed to iterators), we can normally only detect interference after actions, not before.
		 * 我们使用forEach(与迭代器相反),我们通常在执行完操作后再做检查,而不是之前
		 *
		 * Further CME-triggering checks apply to all other possible violations of assumptions for example null or too-small
		 * elementData array given its size(), that could only have occurred due to interference.
		 *
		 * This allows the inner loop of forEach to run without any further checks, and simplifies lambda-resolution.
		 *
		 * While this does entail a number of checks, note that in the common case of
		 * list.stream().forEach(a), no checks or other computation occur anywhere other than inside forEach itself.
		 *
		 * The other less-often-used methods cannot take advantage of most of these streamlinings.
		 */
		//list reference
		private final ArrayList<E> list;
		// 当前索引
		private int index; // current index, modified on advance/split
		// index上一次指向的位置
		private int fence; // -1 until used; then one past last index
		//fast-fail
		private int expectedModCount; // initialized when fence set

		/**
		 * Create new spliterator covering the given range
		 */
		ArrayListSpliterator( ArrayList<E> list, int origin, int fence,
		                      int expectedModCount ) {
			this.list = list; // OK if null unless traversed
			this.index = origin;
			this.fence = fence;
			this.expectedModCount = expectedModCount;
		}

		//获取上一次指针指向位置
		private int getFence() { // initialize fence to size on first use
			int hi; // (a specialized variant appears in method forEach)
			ArrayList<E> lst;
			if ( (hi = fence) < 0 ) {
				if ( (lst = list) == null )
					hi = fence = 0;
				else {
					expectedModCount = lst.modCount;
					hi = fence = lst.size;
				}
			}
			return hi;
		}

		public ArrayList.ArrayListSpliterator<E> trySplit() {
			int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
			return (lo >= mid) ? null : // divide range in half unless too small
					new ArrayList.ArrayListSpliterator<E>( list, lo, index = mid,
							expectedModCount );
		}

		public boolean tryAdvance( Consumer<? super E> action ) {
			if ( action == null )
				throw new NullPointerException();
			int hi = getFence(), i = index;
			if ( i < hi ) {
				index = i + 1;
				@SuppressWarnings("unchecked") E e = (E) list.elementData[i];
				action.accept( e );
				if ( list.modCount != expectedModCount )
					throw new ConcurrentModificationException();
				return true;
			}
			return false;
		}

		public void forEachRemaining( Consumer<? super E> action ) {
			int i, hi, mc; // hoist accesses and checks from loop
			ArrayList<E> lst;
			Object[] a;
			if ( action == null )
				throw new NullPointerException();
			if ( (lst = list) != null && (a = lst.elementData) != null ) {
				if ( (hi = fence) < 0 ) {
					mc = lst.modCount;
					hi = lst.size;
				} else
					mc = expectedModCount;
				if ( (i = index) >= 0 && (index = hi) <= a.length ) {
					for ( ; i < hi ; ++i ) {
						@SuppressWarnings("unchecked") E e = (E) a[i];
						action.accept( e );
					}
					if ( lst.modCount == mc )
						return;
				}
			}
			throw new ConcurrentModificationException();
		}

		public long estimateSize() {
			return (long) (getFence() - index);
		}

		public int characteristics() {
			return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
		}
	}

	@Override
	public boolean removeIf( Predicate<? super E> filter ) {
		Objects.requireNonNull( filter );
		// figure out which elements are to be removed
		// any exception thrown from the filter predicate at this stage
		// will leave the collection unmodified
		int removeCount = 0;
		final BitSet removeSet = new BitSet( size );
		final int expectedModCount = modCount;
		final int size = this.size;
		for ( int i = 0 ; modCount == expectedModCount && i < size ; i++ ) {
			@SuppressWarnings("unchecked")
			final E element = (E) elementData[i];
			if ( filter.test( element ) ) {
				removeSet.set( i );
				removeCount++;
			}
		}
		if ( modCount != expectedModCount ) {
			throw new ConcurrentModificationException();
		}

		// shift surviving elements left over the spaces left by removed elements
		final boolean anyToRemove = removeCount > 0;
		if ( anyToRemove ) {
			final int newSize = size - removeCount;
			for ( int i = 0, j = 0 ; (i < size) && (j < newSize) ; i++, j++ ) {
				i = removeSet.nextClearBit( i );
				elementData[j] = elementData[i];
			}
			for ( int k = newSize ; k < size ; k++ ) {
				elementData[k] = null;  // Let gc do its work
			}
			this.size = newSize;
			if ( modCount != expectedModCount ) {
				throw new ConcurrentModificationException();
			}
			modCount++;
		}

		return anyToRemove;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void replaceAll( UnaryOperator<E> operator ) {
		Objects.requireNonNull( operator );
		final int expectedModCount = modCount;
		final int size = this.size;
		for ( int i = 0 ; modCount == expectedModCount && i < size ; i++ ) {
			elementData[i] = operator.apply( (E) elementData[i] );
		}
		if ( modCount != expectedModCount ) {
			throw new ConcurrentModificationException();
		}
		modCount++;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void sort( Comparator<? super E> c ) {
		final int expectedModCount = modCount;
		Arrays.sort( (E[]) elementData, 0, size, c );
		if ( modCount != expectedModCount ) {
			throw new ConcurrentModificationException();
		}
		modCount++;
	}
//}

}
