package com.chung.source.reading.collections.source.list;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * The {@code Vector} class implements a growable array of objects.
 * Vector类实现了一个可以存放对象且增长的数组.
 * <p>
 * Like an array, it contains components that can be accessed using an integer index.
 * 像数组一样,它可以使用index下标来访问数组中对应位置的元素.
 * <p>
 * However, the size of a
 * {@code Vector} can grow or shrink as needed to accommodate
 * adding and removing items after the {@code Vector} has been created.
 * 在vector创建后,将会在添加或移除元素时增长或收缩数组大小.
 * <p>
 * <p>Each vector tries to optimize storage management by maintaining a
 * {@code capacity} and a {@code capacityIncrement}.
 * 使用capacity及capacityIncrement参数来管理每一个vector的大小.
 * <p>
 * The {@code capacity} is always at least as large as the vector size;
 * capacity为初始化时的大小
 * <p>
 * it is usually larger because as components are added to the
 * vector, the vector's storage increases in chunks the size of
 * {@code capacityIncrement}.
 * capacity通常作为一个vector的元素被添加到该类中,每次增长大小由capacityIncrement参数决定
 * <p>
 * An application can increase the capacity of a vector before inserting a large number of components;
 * 应用程序在插入大量组件之前,先增加向量的容量;
 * <p>
 * this reduces the amount of incremental reallocation.
 * 这减少了增量重新分配的数量
 * <p>
 * <p><a name="fail-fast">
 * The iterators returned by this class's {@link #iterator() iterator} and
 * {@link #listIterator(int) listIterator} methods are <em>fail-fast</em></a>:
 * 本类中的iterator方法及listIterator方法是fail-fast的:
 * <p>
 * if the vector is structurally modified at any time after the iterator is
 * created, in any way except through the iterator's own {@link ListIterator#remove() remove}
 * or {@link ListIterator#add(Object) add} methods, the iterator will throw a
 * {@link ConcurrentModificationException}.
 * 如果vector的结构在迭代器创建后的任何时刻发生改变(不是通过迭代器调用remove方法或add方法时),
 * 迭代器将抛出ConcurrentModificationException异常
 * <p>
 * Thus, in the face of
 * concurrent modification, the iterator fails quickly and cleanly, rather
 * than risking arbitrary, non-deterministic behavior at an undetermined
 * time in the future.
 * 因此迭代器将快速失败,避免后续发生不可预知的错误.
 * <p>
 * The {@link Enumeration Enumerations} returned by the {@link #elements() elements} method are <em>not</em> fail-fast.
 * Enumeration 将在调用elements方法时返回,不过此方法并不是fail-fast的.
 * <p>
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.
 * 请注意,并不能保证迭代器的失败快速行为 因为在一般存在未同步的并发修改的情况下它是不可能作出任何硬保证的.
 * <p>
 * Fail-fast iterators throw {@code ConcurrentModificationException} on a best-effort basis.
 * 故障快速迭代器尽可能的抛出 {@code ConcurrentModificationException}
 * <p>
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:  <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 * 因此,不要在线上环境下依赖该异常去判断对错,快速失败行为仅用于判断是否有bug.
 * <p>
 * <p>As of the Java 2 platform v1.2, this class was retrofitted to
 * implement the {@link List} interface, making it a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 * <p>
 * Unlike the new collection
 * implementations, {@code Vector} is synchronized.  If a thread-safe
 * implementation is not needed, it is recommended to use {@link
 * java.util.ArrayList} in place of {@code Vector}.
 * 注意:该类是同步的.如果不需要线程安全,则推荐使用ArrayList来代替Vector.
 *
 * @author Lee Boynton
 * @author Jonathan Payne
 * @see Collection
 * @see LinkedList
 * @since JDK1.0
 * <p>
 * Created by Chung.
 * Usage:
 * Description:
 * Create dateTime: 17/5/31
 */
public class Vector<E>
		extends AbstractList<E>
		implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
	/**
	 * The array buffer into which the components of the vector are
	 * stored.
	 * 保存Vector中数据的数组
	 * <p>
	 * The capacity of the vector is the length of this array buffer,
	 * and is at least large enough to contain all the vector's elements.
	 * capacity代表该数组长度,它的长度至少需要满足创建后的数组包含该vector中所有的元素.
	 * <p>
	 * <p>Any array elements following the last element in the Vector are null.
	 * 向量对象中,最后一个元素后面的任何数组元素 都是 null
	 *
	 * @serial
	 */
	protected Object[] elementData;

	/**
	 * 实际数据的数量
	 * <p>
	 * The number of valid components in this {@code Vector} object.
	 * Components {@code elementData[0]} through
	 * {@code elementData[elementCount-1]} are the actual items.
	 * <p>
	 * 返回该Vector中包含的有效元素数量,如elementCount=10 那么如果想获取第10个元素 那么实际元素存放在elementData[9]位置
	 *
	 * @serial
	 */
	protected int elementCount;

	/**
	 * 容量增长系数
	 * <p>
	 * The amount by which the capacity of the vector is automatically
	 * incremented when its size becomes greater than its capacity.
	 * 当vector需要自动扩容时(需要大于capacity初始容量),将使用该变量的值.
	 * <p>
	 * If the capacity increment is less than or equal to zero, the capacity
	 * of the vector is doubled each time it needs to grow.
	 * 如果该变量的值小于等于0,那么vector将会在每次增长时使用增长因子2,即每次将会将容量*2作为本次扩容后的容量.
	 *
	 * @serial
	 */
	protected int capacityIncrement;

	/**
	 * Vector的序列版本号 use serialVersionUID from JDK 1.0.2 for interoperability
	 */
	private static final long serialVersionUID = -2767605614048989439L;

	/**
	 * Constructs an empty vector with the specified initial capacity and
	 * capacity increment.
	 * 构造器,构造一个指定初始化容量及增长大小的vector.
	 *
	 * @param initialCapacity   the initial capacity of the vector
	 *                          vector初始化容量
	 * @param capacityIncrement the amount by which the capacity is
	 *                          increased when the vector overflows
	 *                          当vector容量不够时将自动增长capacityIncrement个数组容量
	 * @throws IllegalArgumentException if the specified initial capacity
	 *                                  is negative
	 *                                  如果指定的initialCapacity<0则抛出磁异常
	 */
	public Vector( int initialCapacity, int capacityIncrement ) {
		super();
		if ( initialCapacity < 0 )
			throw new IllegalArgumentException( "Illegal Capacity: " +
					initialCapacity );
		this.elementData = new Object[initialCapacity];
		this.capacityIncrement = capacityIncrement;
	}

	/**
	 * Constructs an empty vector with the specified initial capacity and
	 * with its capacity increment equal to zero.
	 * 构造器,构造一个指定初始化容量的vector,自动扩容时的增长大小设置为0
	 *
	 * @param initialCapacity the initial capacity of the vector
	 *                        vector初始化容量
	 * @throws IllegalArgumentException if the specified initial capacity
	 *                                  is negative
	 *                                  如果指定的initialCapacity<0则抛出磁异常
	 */
	public Vector( int initialCapacity ) {
		this( initialCapacity, 0 );
	}

	/**
	 * Constructs an empty vector so that its internal data array
	 * has size {@code 10} and its standard capacity increment is
	 * zero.
	 * 构造器,返回一个空的vector,其存放元素的数组大小为0,自动扩容时的增长大小设置为0
	 */
	public Vector() {
		this( 10 );
	}

	/**
	 * Constructs a vector containing the elements of the specified
	 * collection, in the order they are returned by the collection's
	 * iterator.
	 * 构造一个vector包含指定集合中的元素,顺序按照传入集合的迭代器返回顺序决定.
	 *
	 * @param c the collection whose elements are to be placed into this
	 *          vector
	 *          源集合
	 * @throws NullPointerException if the specified collection is null 如果传入的集合为null则抛出空指针异常
	 * @since 1.2
	 */
	public Vector( Collection<? extends E> c ) {
		elementData = c.toArray();
		elementCount = elementData.length;
		// c.toArray might (incorrectly) not return Object[] (see 6260652)
		if ( elementData.getClass() != Object[].class )
			elementData = Arrays.copyOf( elementData, elementCount, Object[].class );
	}

	/**
	 * Copies the components of this vector into the specified array.
	 * 拷贝vector中的元素到指定的数组中
	 * <p>
	 * The item at index {@code k} in this vector is copied into
	 * component {@code k} of {@code anArray}.
	 * 在新数组中的下标k与在vector中的下标k相同
	 *
	 * @param anArray the array into which the components get copied 结果集存放数组
	 * @throws NullPointerException      if the given array is null 如果给定的数组为null
	 * @throws IndexOutOfBoundsException if the specified array is not
	 *                                   large enough to hold all the components of this vector
	 *                                   如果指定的数组长度不够容纳vector中的所有元素
	 * @throws ArrayStoreException       if a component of this vector is not of
	 *                                   a runtime type that can be stored in the specified array
	 *                                   如果vector中元素类型与结果集数组类型不一致
	 * @see #toArray(Object[])
	 */
	public synchronized void copyInto( Object[] anArray ) {
		System.arraycopy( elementData, 0, anArray, 0, elementCount );
	}

	/**
	 * Trims the capacity of this vector to be the vector's current size.
	 * 缩减vector大小至当前vector的大小.
	 * <p>
	 * If the capacity of this vector is larger than its current
	 * size, then the capacity is changed to equal the size by replacing
	 * its internal data array, kept in the field {@code elementData},
	 * with a smaller one.
	 * 如果vector的容量比当前的大小多的话,capacity值将被设置成vector内数组的大小.
	 * <p>
	 * An application can use this operation to
	 * minimize the storage of a vector.
	 * 通过本操作,可以最小化该vector的存储容量.
	 */
	public synchronized void trimToSize() {
		//快速失败计数++
		modCount++;
		//获取现有长度
		int oldCapacity = elementData.length;
		if ( elementCount < oldCapacity ) {
			//重新为数组分配大小
			elementData = Arrays.copyOf( elementData, elementCount );
		}
	}

	/**
	 * Increases the capacity of this vector, if necessary, to ensure
	 * that it can hold at least the number of components specified by
	 * the minimum capacity argument.
	 * 给vector扩容.如果必要,需要确保最小数量满足实际存储元素的数量.
	 * <p>
	 * <p>
	 * <p>If the current capacity of this vector is less than
	 * {@code minCapacity}, then its capacity is increased by replacing its
	 * internal data array, kept in the field {@code elementData}, with a
	 * larger one.
	 * 如果当前的容量capacity小于最小容量,
	 * <p>
	 * The size of the new data array will be the old size plus
	 * {@code capacityIncrement},
	 * 新的数组大小将等于原有大小+增长因子大小,除非capacityIncrement<=0,当其小于等于0时新的容量将=原有容量*2
	 * unless the value of {@code capacityIncrement} is less than or equal to zero, in which case
	 * the new capacity will be twice the old capacity;
	 * <p>
	 * but if this new size
	 * is still smaller than {@code minCapacity}, then the new capacity will
	 * be {@code minCapacity}.
	 * 如果新的大小仍然小于最小容量大小,则新的容量将等于原有大小
	 *
	 * @param minCapacity the desired minimum capacity 希望指定的最小容量
	 */
	public synchronized void ensureCapacity( int minCapacity ) {
		if ( minCapacity > 0 ) {
			modCount++;
			ensureCapacityHelper( minCapacity );
		}
	}

	/**
	 * This implements the unsynchronized semantics of ensureCapacity.
	 * 本方法为非同步的ensureCapacity方法.
	 * <p>
	 * Synchronized methods in this class can internally call this
	 * method for ensuring capacity without incurring the cost of an
	 * extra synchronization.
	 * 本类中的同步方法在内部调用该方法 将减少额外的同步消耗.
	 *
	 * @see #ensureCapacity(int)
	 */
	private void ensureCapacityHelper( int minCapacity ) {
		// overflow-conscious code
		if ( minCapacity - elementData.length > 0 )
			grow( minCapacity );
	}

	/**
	 * The maximum size of array to allocate.
	 * 数组可以分配的最大容量.
	 * Some VMs reserve some header words in an array.
	 * 一些虚拟机在数组中保留了一些原语.
	 * Attempts to allocate larger arrays may result in
	 * 所以需要-8来保证是安全的
	 * OutOfMemoryError: Requested array size exceeds VM limit
	 */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	/**
	 * 扩容内容数组
	 *
	 * @param minCapacity 扩充后最小容量
	 */
	private void grow( int minCapacity ) {
		// overflow-conscious code
		// 获取原有容量   =元素数组长度
		int oldCapacity = elementData.length;
		// 计算新的容量 = 增长因子>0则为原有容量+增长因子 否则新的容量将=原有容量的2倍
		int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
				capacityIncrement : oldCapacity);
		// 如果新的容量小于最小容量 则新的容量等于最小容量
		if ( newCapacity - minCapacity < 0 )
			newCapacity = minCapacity;
		// 如果新的容量大于最大容量 则赋值一个安全的容量
		if ( newCapacity - MAX_ARRAY_SIZE > 0 )
			newCapacity = hugeCapacity( minCapacity );
		// 执行拷贝操作 将原有数组中元素拷贝到新的数组中 新的数组大小为newCapacity
		elementData = Arrays.copyOf( elementData, newCapacity );
	}

	/**
	 * 返回一个最大的容量
	 *
	 * @param minCapacity 最小容量
	 * @return 最大容量
	 */
	private static int hugeCapacity( int minCapacity ) {
		//防止溢出
		if ( minCapacity < 0 ) // overflow
			throw new OutOfMemoryError();
		//如果最小容量大于安全的数组大小 则返回INT最大值 否则返回安全数组大小
		return (minCapacity > MAX_ARRAY_SIZE) ?
				Integer.MAX_VALUE :
				MAX_ARRAY_SIZE;
	}

	/**
	 * Sets the size of this vector.
	 * 对vector设置大小.
	 * <p>
	 * If the new size is greater than the
	 * current size, new {@code null} items are added to the end of
	 * the vector.
	 * 如果新的大小比现在的数组大小大,那么多余出来的数组空闲位置将会保持null元素.
	 * <p>
	 * If the new size is less than the current size, all
	 * components at index {@code newSize} and greater are discarded.
	 * 如果新的大小比现在的大小 小,那么数组下标从newSize往后的元素将会被置null.(PS.数组大小不变)
	 *
	 * @param newSize the new size of this vector 此vector的新大小
	 * @throws ArrayIndexOutOfBoundsException if the new size is negative 如果新的大小不可用则抛出数组越界
	 */
	public synchronized void setSize( int newSize ) {
		// 快速失败计数
		modCount++;
		//如果大则扩容 扩容起始位置后的元素置null
		if ( newSize > elementCount ) {
			ensureCapacityHelper( newSize );
		} else {
			//从newSize位置开始 数组中的各元素置null
			for ( int i = newSize ; i < elementCount ; i++ ) {
				elementData[i] = null;
			}
		}
		//刷新vector中的元素个数变量
		elementCount = newSize;
	}

	/**
	 * Returns the current capacity of this vector.
	 * 返回当前vector的容量.
	 *
	 * @return the current capacity (the length of its internal
	 * data array, kept in the field {@code elementData}
	 * of this vector)
	 * 返回vector中元素数组的长度.
	 */
	public synchronized int capacity() {
		return elementData.length;
	}

	/**
	 * Returns the number of components in this vector.
	 * 返回vector中的元素个数 注意size方法和capacity方法返回值可能不相等.
	 * <p>
	 * 一个是返回实际元素数组的大小,一个是返回元素数量.
	 * 如:假设调用前两方法均返回10,那么现在调用过setSize方法后,size方法返回5而capacity将继续返回10,
	 * 因为setSize方法在传入的新size比原有数组内元素个数小 时并没有改变原有数组,仅仅执行了置空操作.
	 *
	 * @return the number of components in this vector
	 * 返回vector中的元素个数
	 */
	public synchronized int size() {
		return elementCount;
	}

	/**
	 * Tests if this vector has no components.
	 * 判断vector是否包含元素.
	 *
	 * @return {@code true} if and only if this vector has
	 * no components, that is, its size is zero;
	 * 当且仅当vector中没有包含任何元素时,它的大小是0
	 * <p>
	 * {@code false} otherwise.
	 */
	public synchronized boolean isEmpty() {
		return elementCount == 0;
	}

	/**
	 * Returns an enumeration of the components of this vector.
	 * 返回包含当前vector中元素的Enumeration集合.
	 * <p>
	 * The returned {@code Enumeration} object will generate all items in
	 * this vector.
	 * 通过返回的Enumeration可以遍历该vector中的所有元素.
	 * <p>
	 * The first item generated is the item at index {@code 0},
	 * then the item at index {@code 1}, and so on.
	 * 第一个元素的index=0 第二个元素的index=1 以此类推.
	 *
	 * @return an enumeration of the components of this vector 返回该vector的enumeration组件
	 * @see Iterator
	 */
	public Enumeration<E> elements() {
		return new Enumeration<E>() {
			//计数遍历 index 根据该值计算返回哪一个元素.
			int count = 0;

			//判断是否有下一个元素
			public boolean hasMoreElements() {
				return count < elementCount;
			}

			//获取下一个元素
			public E nextElement() {
				//类 锁
				synchronized (Vector.this) {
					//合法性校验 避免越界
					if ( count < elementCount ) {
						//返回count所指元素 并执行++操作
						return elementData( count++ );
					}
				}
				//如果上一步没有返回 则在此处抛出异常
				throw new NoSuchElementException( "Vector Enumeration" );
			}
		};
	}

	/**
	 * Returns {@code true} if this vector contains the specified element.
	 * 返回vector是否包含指定的元素.
	 * <p>
	 * More formally, returns {@code true} if and only if this vector
	 * contains at least one element {@code e} such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 * 更严谨的说,当且仅当vector包含至少一个时返回.
	 *
	 * @param o element whose presence in this vector is to be tested
	 *          需要被判断是否在vector中存在的元素
	 * @return {@code true} if this vector contains the specified element
	 * 如果该vector包含指定元素则返回true
	 */
	public boolean contains( Object o ) {
		//实际原理是遍历数组 找到第一次出现的位置 而后与0判断 不存在返回-1 即false
		return indexOf( o, 0 ) >= 0;
	}

	/**
	 * Returns the index of the first occurrence of the specified element
	 * in this vector, or -1 if this vector does not contain the element.
	 * 返回vector中该元素出现的第一个index,如果返回-1则代表该vector不包含此元素.
	 * <p>
	 * More formally, returns the lowest index {@code i} such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 * 更严谨的说 是返回第一次出现的index 即最小下标.
	 *
	 * @param o element to search for 需要被找寻的元素
	 * @return the index of the first occurrence of the specified element in
	 * this vector, or -1 if this vector does not contain the element
	 * 返回第一次出现位置的下标,如果返回-1则代表不包含此元素.
	 */
	public int indexOf( Object o ) {
		return indexOf( o, 0 );
	}

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this vector, searching forwards from {@code index}, or returns -1 if
	 * the element is not found.
	 * 从index传入的位置开始往后遍历数组 查找传入的对象o 找到则返回o对应的下标, 否则返回-1 则代表没找到.
	 * <p>
	 * More formally, returns the lowest index {@code i} such that
	 * <tt>(i&nbsp;&gt;=&nbsp;index&nbsp;&amp;&amp;&nbsp;(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i))))</tt>,
	 * or -1 if there is no such index.
	 * 更严谨的说 是第一次的下标 最小下标.
	 *
	 * @param o     element to search for 需要被找寻的元素
	 * @param index index to start searching from 循环的起始位置
	 * @return the index of the first occurrence of the element in
	 * this vector at position {@code index} or later in the vector;
	 * {@code -1} if the element is not found.
	 * 返回第一次出现的元素下标,或者后来出现的下标,如果返回-1则代表该元素在本vector中未找到.
	 * @throws IndexOutOfBoundsException if the specified index is negative 如果index不可用则抛出此异常
	 * @see Object#equals(Object)
	 */
	public synchronized int indexOf( Object o, int index ) {
		//元素为null时
		if ( o == null ) {
			//遍历数组 返回第一个null元素出现的index位置
			for ( int i = index ; i < elementCount ; i++ )
				if ( elementData[i] == null )
					return i;
		} else {
			//遍历数组 返回与该元素相等时对应元素的index位置
			for ( int i = index ; i < elementCount ; i++ )
				if ( o.equals( elementData[i] ) )
					return i;
		}
		//未找到返回-1
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element
	 * in this vector, or -1 if this vector does not contain the element.
	 * 返回vector中该元素最后一次出现的位置,从后向前找.如果返回-1则代表该vector不包含该元素.
	 * <p>
	 * More formally, returns the highest index {@code i} such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 * 更严谨的说,返回该元素的最高index
	 *
	 * @param o element to search for 需要被找寻的元素
	 * @return the index of the last occurrence of the specified element in
	 * this vector, or -1 if this vector does not contain the element
	 * 返回该元素最后一次出现位置的下标,如果返回-1则代表该元素在vector中不存在
	 */
	public synchronized int lastIndexOf( Object o ) {
		return lastIndexOf( o, elementCount - 1 );
	}

	/**
	 * Returns the index of the last occurrence of the specified element in
	 * this vector, searching backwards from {@code index}, or returns -1 if
	 * the element is not found.
	 * 返回指定元素在vector中最后一次出现位置的index,从后向前找寻,如果返回-1则代表该元素未找到.
	 * <p>
	 * More formally, returns the highest index {@code i} such that
	 * <tt>(i&nbsp;&lt;=&nbsp;index&nbsp;&amp;&amp;&nbsp;(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i))))</tt>,
	 * or -1 if there is no such index.
	 * 更严谨的说,返回的是最高的一个index 如果返回-1则代表该元素在vector中不存在
	 *
	 * @param o     element to search for 需要被找寻的元素
	 * @param index index to start searching backwards from 起始的index位置 从此处向前查找
	 * @return the index of the last occurrence of the element at position
	 * less than or equal to {@code index} in this vector;
	 * -1 if the element is not found.
	 * 如果返回-1则代表该元素不存在,否则返回在vector中最后一次出现的位置,即最高下标
	 * @throws IndexOutOfBoundsException if the specified index is greater
	 *                                   than or equal to the current size of this vector
	 *                                   如果指定的index比当前vector的大小 大 则抛出越界异常
	 */
	public synchronized int lastIndexOf( Object o, int index ) {
		//判断避免数组越界
		if ( index >= elementCount )
			throw new IndexOutOfBoundsException( index + " >= " + elementCount );
		//查找空元素 从后向前找
		if ( o == null ) {
			for ( int i = index ; i >= 0 ; i-- )
				if ( elementData[i] == null )
					return i;
		} else {
			//其他元素 从后向前找
			for ( int i = index ; i >= 0 ; i-- )
				if ( o.equals( elementData[i] ) )
					return i;
		}
		//查找不到则返回-1
		return -1;
	}

	/**
	 * Returns the component at the specified index.
	 * 返回指定下标index处对应的元素
	 * <p>
	 * <p>This method is identical in functionality to the {@link #get(int)}
	 * method (which is part of the {@link List} interface).
	 * 本方法与get方法是相同的(作为list接口的一部分)
	 *
	 * @param index an index into this vector 指定index
	 * @return the component at the specified index 返回指定index对应的元素
	 * @throws ArrayIndexOutOfBoundsException if the index is out of range
	 *                                        ({@code index < 0 || index >= size()})
	 *                                        如果index<0或大于等于vector长度则抛出越界异常
	 */
	public synchronized E elementAt( int index ) {
		//判断是否越界
		if ( index >= elementCount ) {
			throw new ArrayIndexOutOfBoundsException( index + " >= " + elementCount );
		}
		//获取该位置对应的元素
		return elementData( index );
	}

	/**
	 * Returns the first component (the item at index {@code 0}) of
	 * this vector.
	 * 返回在vector中的第一个元素
	 *
	 * @return the first component of this vector 返回第一个元素
	 * @throws NoSuchElementException if this vector has no components
	 *                                如果不存在此元素则抛出该异常
	 */
	public synchronized E firstElement() {
		//判断是否为空vector
		if ( elementCount == 0 ) {
			throw new NoSuchElementException();
		}
		//获取第0号位置元素
		return elementData( 0 );
	}

	/**
	 * Returns the last component of the vector.
	 * 返回vector的最后一个元素.
	 *
	 * @return the last component of the vector, i.e., the component at index
	 * <code>size()&nbsp;-&nbsp;1</code>.
	 * 返回vector中的最后一个元素 index值应为size-1
	 * @throws NoSuchElementException if this vector is empty
	 *                                如果vector是空的则将抛出NoSuchElementException异常
	 */
	public synchronized E lastElement() {
		//防止越界
		if ( elementCount == 0 ) {
			throw new NoSuchElementException();
		}
		return elementData( elementCount - 1 );
	}

	/**
	 * Sets the component at the specified {@code index} of this
	 * vector to be the specified object.
	 * 设置vector的指定位置为传入的元素
	 * <p>
	 * The previous component at that
	 * position is discarded.
	 * 先前该位置的元素将会被忽略
	 * <p>
	 * <p>The index must be a value greater than or equal to {@code 0}
	 * and less than the current size of the vector.
	 * index必须大于或等于0且小于vector当前的元素数量.
	 * <p>
	 * <p>This method is identical in functionality to the
	 * {@link #set(int, Object) set(int, E)}
	 * method (which is part of the {@link List} interface).
	 * 本方法为list接口下的重载方法
	 * <p>
	 * Note that the {@code set} method reverses the order of the parameters, to more closely match array usage.
	 * 注意,本方法将会反转参数的使用顺序,以便更紧密地匹配数组使用情况.
	 * <p>
	 * Note also that the {@code set} method returns the
	 * old value that was stored at the specified position.
	 * 注意,本方法不会像本类中的set方法一样返回该特定位置原有的元素.
	 *
	 * @param obj   what the component is to be set to  需要被设置的元素
	 * @param index the specified index 特定的位置
	 * @throws ArrayIndexOutOfBoundsException if the index is out of range
	 *                                        ({@code index < 0 || index >= size()})
	 *                                        如果index越界则抛出此异常.
	 */
	public synchronized void setElementAt( E obj, int index ) {
		//越界检查
		if ( index >= elementCount ) {
			throw new ArrayIndexOutOfBoundsException( index + " >= " +
					elementCount );
		}
		elementData[index] = obj;
	}

	/**
	 * Deletes the component at the specified index.
	 * 删除特定index位置的元素.
	 * <p>
	 * Each component in this vector with an index greater or equal to the specified
	 * {@code index} is shifted downward to have an index one
	 * smaller than the value it had previously. The size of this vector
	 * is decreased by {@code 1}.
	 * vector中指定index位置的元素将会被移除,在这个index位置元素之后的元素将会顺序向前移动一位.vector大小--.
	 * <p>
	 * <p>
	 * <p>The index must be a value greater than or equal to {@code 0}
	 * and less than the current size of the vector.
	 * index必须大于或等于0且小于vector当前的元素数量.
	 * <p>
	 * <p>
	 * <p>This method is identical in functionality to the {@link #remove(int)}
	 * method (which is part of the {@link List} interface).
	 * 功能上来说它与remove方法相同,且是List接口的一部分.
	 * <p>
	 * Note that the {@code remove} method returns the old value that was stored at the specified position.
	 * 注意,remove方法才会返回该位置的原有元素值,本方法不会.
	 *
	 * @param index the index of the object to remove 需要被移除元素的index位置
	 * @throws ArrayIndexOutOfBoundsException if the index is out of range
	 *                                        ({@code index < 0 || index >= size()})
	 *                                        如果index越界则抛出此异常.
	 */
	public synchronized void removeElementAt( int index ) {
		//fast-failed处理
		modCount++;
		//越界检查
		if ( index >= elementCount ) {
			throw new ArrayIndexOutOfBoundsException( index + " >= " +
					elementCount );
		} else if ( index < 0 ) {
			throw new ArrayIndexOutOfBoundsException( index );
		}
		//计算从需要移动元素的长度
		//计算方式: 元素个数或者说是需要被前移的元素长度 = 元素数量 - 指定位置 - 1
		int j = elementCount - index - 1;
		if ( j > 0 ) {
			System.arraycopy( elementData, index + 1, elementData, index, j );
		}
		//vector元素数量--
		elementCount--;
		//将原有最后一位置null方便gc
		elementData[elementCount] = null; /* to let gc do its work */
	}

	/**
	 * Inserts the specified object as a component in this vector at the
	 * specified {@code index}.
	 * 在vector的指定位置插入元素.
	 * <p>
	 * Each component in this vector with
	 * an index greater or equal to the specified {@code index} is
	 * shifted upward to have an index one greater than the value it had
	 * previously.
	 * vector中指定元素将会被插入到index位置,在这个index位置元素之后的元素(包含该位置原有元素)将会顺序向后移动一位.vector大小++.
	 * <p>
	 * <p>
	 * <p>The index must be a value greater than or equal to {@code 0}
	 * and less than or equal to the current size of the vector.
	 * index必须大于或等于0且小于等于vector当前的元素数量.
	 * (If the
	 * index is equal to the current size of the vector, the new element
	 * is appended to the Vector.)
	 * 如果index等于当前vector大小,则新的元素将会被追加到vector中.
	 * <p>
	 * <p>This method is identical in functionality to the {@link #add(int, Object) add(int, E)} method (which is part of the {@link List} interface).
	 * 本方法功能上与add方法一致,且作为List接口的一部分.
	 * <p>
	 * Note that the {@code add} method reverses the order of the parameters, to more closely match array usage.
	 * 注意,本方法将会反转参数的使用顺序,以便更紧密地匹配数组使用情况.
	 *
	 * @param obj   the component to insert           被插入的元素
	 * @param index where to insert the new component 需要被插入的位置
	 * @throws ArrayIndexOutOfBoundsException if the index is out of range
	 *                                        ({@code index < 0 || index > size()})
	 *                                        如果index越界则抛出此异常.
	 */
	public synchronized void insertElementAt( E obj, int index ) {
		//fast-failed处理
		modCount++;
		//越界检查
		if ( index > elementCount ) {
			throw new ArrayIndexOutOfBoundsException( index
					+ " > " + elementCount );
		}
		//扩容
		ensureCapacityHelper( elementCount + 1 );
		//先将该位置的元素顺序后移 如果index等于当前vector长度则本方法调用结果与未调用前相同即没有任何的元素移动
		System.arraycopy( elementData, index, elementData, index + 1, elementCount - index );
		//向特定位置放入元素
		elementData[index] = obj;
		//元素数量++
		elementCount++;
	}

	/**
	 * Adds the specified component to the end of this vector,
	 * increasing its size by one.
	 * 向vector尾部添加一个元素,增长1个长度.
	 * <p>
	 * The capacity of this vector is increased if its size becomes greater than its capacity.
	 * 如果vector的大小大于它的容量那么它将被增加.
	 * <p>
	 * <p>This method is identical in functionality to the
	 * {@link #add(Object) add(E)}
	 * method (which is part of the {@link List} interface).
	 * 本方法与add方法功能相同,且作为List接口的一部分.
	 *
	 * @param obj the component to be added 需要被添加的元素
	 */
	public synchronized void addElement( E obj ) {
		//fast-failed处理
		modCount++;
		//至少增加一个元素
		ensureCapacityHelper( elementCount + 1 );
		//向尾部赋值且元素数量+1
		elementData[elementCount++] = obj;
	}

	/**
	 * Removes the first (lowest-indexed) occurrence of the argument
	 * from this vector.
	 * 从vector中移除第一次出现的最低下标的元素.
	 * <p>
	 * <p>
	 * If the object is found in this vector, each
	 * component in the vector with an index greater or equal to the
	 * object's index is shifted downward to have an index one smaller
	 * than the value it had previously.
	 * 如果该元素在vector中存在,则在数组中该元素之后的元素顺序向前移动一位.
	 * <p>
	 * <p>This method is identical in functionality to the
	 * {@link #remove(Object)} method (which is part of the
	 * {@link List} interface).
	 * 该方法与remove方法功能相同且为List接口的一部分.
	 *
	 * @param obj the component to be removed  需要被移除的元素
	 * @return {@code true} if the argument was a component of this
	 * vector; {@code false} otherwise.
	 * 如果被移除的元素在vector中存在且被移除则返回true否则返回false
	 */
	public synchronized boolean removeElement( Object obj ) {
		//fast-failed处理
		modCount++;
		//获取该元素最低的下标 即顺序遍历找到第一次该元素出现位置的下标
		int i = indexOf( obj );
		//如果找到该元素则执行移除操作并返回true
		if ( i >= 0 ) {
			removeElementAt( i );
			return true;
		}
		//否则返回false
		return false;
	}

	/**
	 * Removes all components from this vector and sets its size to zero.
	 * 从vector中移除所有元素且将vector的size置为0.
	 * <p>
	 * <p>This method is identical in functionality to the {@link #clear}
	 * method (which is part of the {@link List} interface).
	 * 本方法功能同clear方法且属于List接口的一部分.
	 */
	public synchronized void removeAllElements() {
		//fast-failed处理
		modCount++;
		// Let gc do its work 方便gc
		for ( int i = 0 ; i < elementCount ; i++ )
			elementData[i] = null;
		//元素个数置0
		elementCount = 0;
	}

	/**
	 * Returns a clone of this vector.
	 * 返回vector的一份拷贝.
	 * <p>
	 * The copy will contain a
	 * reference to a clone of the internal data array, not a reference
	 * to the original internal data array of this {@code Vector} object.
	 * 拷贝将包含被拷贝vector的一份单独拷贝且修改返回拷贝中的对象引用数组时将不会影响当前被拷贝的vector.
	 *
	 * @return a clone of this vector 一份vector的拷贝
	 */
	public synchronized Object clone() {
		try {
			//将当前vector克隆一份
			@SuppressWarnings("unchecked")
			Vector<E> v = (Vector<E>) super.clone();
			//执行拷贝元素数组操作 源数组为被拷贝vector的元素数组 拷贝长度为被拷贝vector的元素个数
			v.elementData = Arrays.copyOf( elementData, elementCount );

			//fast-failed处理
			v.modCount = 0;
			//返回克隆出来的对象
			return v;
		} catch ( CloneNotSupportedException e ) {
			// this shouldn't happen, since we are Cloneable
			// 这个异常将不会被抛出,从我们有了Cloneable接口开始
			throw new InternalError( e );
		}
	}

	/**
	 * Returns an array containing all of the elements in this Vector
	 * in the correct order.
	 * 返回Vector包含所有元素的一个数组顺序与Vector保存的顺序一致.
	 *
	 * @since 1.2
	 */
	public synchronized Object[] toArray() {
		return Arrays.copyOf( elementData, elementCount );
	}

	/**
	 * Returns an array containing all of the elements in this Vector in the
	 * correct order;
	 * 返回一个包含Vector中所有元素的数组,且顺序与Vector存储的顺序一致.
	 * <p>
	 * the runtime type of the returned array is that of the specified array.
	 * 返回的数组类型应为运行时指定数组的类型.
	 * <p>
	 * If the Vector fits in the specified array, it is returned therein.
	 * 如果Vector适用于该数组类型,则它将被返回.
	 * <p>
	 * Otherwise, a new array is allocated with the runtime
	 * type of the specified array and the size of this Vector.
	 * 此外,一个新的数组的类型与长度按照Vector中的参数在运行时被分配.
	 * <p>
	 * <p>If the Vector fits in the specified array with room to spare
	 * (i.e., the array has more elements than the Vector),
	 * the element in the array immediately following the end of the
	 * Vector is set to null.  (This is useful in determining the length
	 * of the Vector <em>only</em> if the caller knows that the Vector
	 * does not contain any null elements.)
	 * 如果Vector适用于该数组,其剩余填充不满的部分将会被null填充.(例如:数组拥有比Vector多的元素)
	 * (该种情况可以在调用者知道Vector中不包含null元素时,用以判定Vector的长度.)
	 *
	 * @param a the array into which the elements of the Vector are to
	 *          be stored, if it is big enough; otherwise, a new array of the
	 *          same runtime type is allocated for this purpose.
	 *          如果传入的数组a够大,则将会被存储Vector中的元素并返回,否则将会分配一个新的数组填充Vector中的元素后进行返回
	 * @return an array containing the elements of the Vector 返回一个包含Vector中元素的数组.
	 * @throws ArrayStoreException  if the runtime type of a is not a supertype
	 *                              of the runtime type of every element in this Vector
	 *                              如果参数数组a的类型不是Vector的元素数组的父类且与Vector的类型不符时将抛出此异常
	 * @throws NullPointerException if the given array is null 如果参数数组a为null时抛出此异常
	 * @since 1.2
	 */
	@SuppressWarnings("unchecked")
	public synchronized <T> T[] toArray( T[] a ) {
		//如果传入的数组长度小于当前vector的元素个数大小
		if ( a.length < elementCount )
			//则分配一个新的数组且执行vector中元素数组的拷贝操作
			return (T[]) Arrays.copyOf( elementData, elementCount, a.getClass() );
		//否则则直接向传入的元素数组中放入Vector中的所有元素
		System.arraycopy( elementData, 0, a, 0, elementCount );
		//如果传入数组的长度大于Vector中元素数量则将最后元素的下一位置null元素
		if ( a.length > elementCount )
			a[elementCount] = null;
		//返回该数组
		return a;
	}

	// Positional Access Operations
	// 位置访问操作

	@SuppressWarnings("unchecked")
	E elementData( int index ) {
		//返回vector中元素数组指定下标位置的元素
		return (E) elementData[index];
	}

	/**
	 * Returns the element at the specified position in this Vector.
	 * 返回vector中位于特定位置index的元素
	 *
	 * @param index index of the element to return 需要被返回元素的index
	 * @return object at the specified index 返回该位置对应的元素
	 * @throws ArrayIndexOutOfBoundsException if the index is out of range
	 *                                        ({@code index < 0 || index >= size()})
	 *                                        越界检查
	 * @since 1.2
	 */
	public synchronized E get( int index ) {
		//越界检查
		if ( index >= elementCount )
			throw new ArrayIndexOutOfBoundsException( index );
		//获取该位置元素
		return elementData( index );
	}

	/**
	 * Replaces the element at the specified position in this Vector with the
	 * specified element.
	 * 将Vector特定位置的元素替换为指定元素.
	 *
	 * @param index   index of the element to replace 需要被替换元素的位置
	 * @param element element to be stored at the specified position 需要在指定位置存放的元素
	 * @return the element previously at the specified position 返回该位置的原有元素
	 * @throws ArrayIndexOutOfBoundsException if the index is out of range
	 *                                        ({@code index < 0 || index >= size()})
	 *                                        越界检查
	 * @since 1.2
	 */
	public synchronized E set( int index, E element ) {
		//越界检查
		if ( index >= elementCount )
			throw new ArrayIndexOutOfBoundsException( index );
		//获取该位置原有元素
		E oldValue = elementData( index );
		//执行替换操作
		elementData[index] = element;
		//返回原有元素
		return oldValue;
	}

	/**
	 * Appends the specified element to the end of this Vector.
	 * 向Vector的末尾追加元素.
	 *
	 * @param e element to be appended to this Vector 需要被追加的元素
	 * @return {@code true} (as specified by {@link Collection#add}) 如果添加成功则返回true,与Collection#add要求的一致
	 * @since 1.2
	 */
	public synchronized boolean add( E e ) {

		//fast-failed处理
		modCount++;
		//扩容至少一个
		ensureCapacityHelper( elementCount + 1 );
		//元素数量++且放置在数组末尾
		elementData[elementCount++] = e;
		//返回直接成功
		return true;
	}

	/**
	 * Removes the first occurrence of the specified element in this Vector
	 * If the Vector does not contain the element, it is unchanged.
	 * 移除Vector中该元素第一次出现位置的元素,如果不包含此元素则Vector不变.
	 * <p>
	 * More formally, removes the element with the lowest index i such that
	 * {@code (o==null ? get(i)==null : o.equals(get(i)))} (if such
	 * an element exists).
	 * 更严谨的说,移除的是最低下标的元素
	 *
	 * @param o element to be removed from this Vector, if present 如果该元素在Vector中存在则将被移除
	 * @return true if the Vector contained the specified element 如果Vector包含此元素则返回true
	 * @since 1.2
	 */
	public boolean remove( Object o ) {
		return removeElement( o );
	}

	/**
	 * Inserts the specified element at the specified position in this Vector.
	 * 在Vector的特定位置插入指定的元素.
	 * <p>
	 * Shifts the element currently at that position (if any) and any
	 * subsequent elements to the right (adds one to their indices).
	 * 在特定位置后的元素将被顺序后移一位.包含该位置原有元素.
	 *
	 * @param index   index at which the specified element is to be inserted 需要被插入元素的下标
	 * @param element element to be inserted 需要被插入的元素
	 * @throws ArrayIndexOutOfBoundsException if the index is out of range
	 *                                        ({@code index < 0 || index > size()})
	 *                                        越界检查
	 * @since 1.2
	 */
	public void add( int index, E element ) {
		insertElementAt( element, index );
	}

	/**
	 * Removes the element at the specified position in this Vector.
	 * 移除Vector特定位置的元素.
	 * <p>
	 * Shifts any subsequent elements to the left (subtracts one from their
	 * indices).  Returns the element that was removed from the Vector.
	 * 在特定位置后的元素将被顺序前移一位.
	 * 并返回该位置原有元素.
	 *
	 * @param index the index of the element to be removed 需要被移除的位置
	 * @return element that was removed 需要被移除的元素
	 * @throws ArrayIndexOutOfBoundsException if the index is out of range
	 *                                        ({@code index < 0 || index >= size()})
	 *                                        越界检查
	 * @since 1.2
	 */
	public synchronized E remove( int index ) {
		//fast-failed处理
		modCount++;
		//越界检查
		if ( index >= elementCount )
			throw new ArrayIndexOutOfBoundsException( index );
		//获取原有元素
		E oldValue = elementData( index );
		//计算需要移动元素的个数 = 所有元素个数-移除位置-1
		int numMoved = elementCount - index - 1;
		//如果需要移动的元素个数大于0则执行移动操作(例子:如果移除的是尾元素则不需要执行此操作)
		if ( numMoved > 0 )
			System.arraycopy( elementData, index + 1, elementData, index,
					numMoved );
		//gc回收
		elementData[--elementCount] = null; // Let gc do its work
		//返回该位置原有旧元素
		return oldValue;
	}

	/**
	 * Removes all of the elements from this Vector.  The Vector will
	 * be empty after this call returns (unless it throws an exception).
	 * 移除该Vector中所有的元素.如果不抛出异常则Vector将会被清空.
	 *
	 * @since 1.2
	 */
	public void clear() {
		removeAllElements();
	}

	// Bulk Operations  批量操作

	/**
	 * Returns true if this Vector contains all of the elements in the
	 * specified Collection.
	 * 当且仅当该Vector包含collection中的所有元素时返回true.
	 *
	 * @param c a collection whose elements will be tested for containment
	 *          in this Vector
	 *          需要被判定是否被Vector包含的集合
	 * @return true if this Vector contains all of the elements in the
	 * specified collection 如果包含collection中的所有元素则返回true
	 * @throws NullPointerException if the specified collection is null 如果指定collection为空则抛出此异常
	 */
	public synchronized boolean containsAll( Collection<?> c ) {
		return super.containsAll( c );
	}

	/**
	 * Appends all of the elements in the specified Collection to the end of
	 * this Vector, in the order that they are returned by the specified
	 * Collection's Iterator.
	 * 将collection中的所有元素追加到Vector原有元素数组之后,追加的顺序同collection的迭代器返回的顺序.
	 * <p>
	 * The behavior of this operation is undefined if
	 * the specified Collection is modified while the operation is in progress.
	 * 如果当本操作进行时,如果被添加集合collection被更改,则发生结果未知.
	 * <p>
	 * (This implies that the behavior of this call is undefined if the specified Collection is this Vector, and this Vector is nonempty.)
	 * 这暗示着:如果指定的集合是空的Vector则调用结果未知.
	 *
	 * @param c elements to be inserted into this Vector 需要被添加到Vector中的集合
	 * @return {@code true} if this Vector changed as a result of the call 如果Vector发生了变化则返回true
	 * @throws NullPointerException if the specified collection is null 如果collection为null则抛出空指针异常
	 * @since 1.2
	 */
	public synchronized boolean addAll( Collection<? extends E> c ) {
		//fast-failed处理
		modCount++;
		//将集合collection转换为对象数组
		Object[] a = c.toArray();
		//获取该数组长度 即被添加的长度
		int numNew = a.length;
		//扩容操作 设定至少应扩容新元素的长度
		ensureCapacityHelper( elementCount + numNew );
		//执行扩容操作 将被添加数组追加到Vector原有对象数组之后
		System.arraycopy( a, 0, elementData, elementCount, numNew );
		//修改Vector中元素数量的计数变量
		elementCount += numNew;
		//判断新添加元素数组的长度是否为0
		return numNew != 0;
	}

	/**
	 * Removes from this Vector all of its elements that are contained in the
	 * specified Collection.
	 * 从Vector中移除collection中的所有元素.
	 *
	 * @param c a collection of elements to be removed from the Vector
	 *          需要从Vector中被移除的元素集合
	 * @return true if this Vector changed as a result of the call
	 * 如果Vector在本操作后发生改变则返回true
	 * @throws ClassCastException   if the types of one or more elements
	 *                              in this vector are incompatible with the specified
	 *                              collection
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 *                              如果指定集合中有一个或更多个元素的类型与Vector的类型不兼容则抛出此异常
	 * @throws NullPointerException if this vector contains one or more null
	 *                              elements and the specified collection does not support null
	 *                              elements
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>),
	 *                              or if the specified collection is null
	 *                              如果Vector包含一个或更多个null元素且指定的collection不支持null元素或collection集合为null则抛出此异常
	 * @since 1.2
	 */
	public synchronized boolean removeAll( Collection<?> c ) {
		return super.removeAll( c );
	}

	/**
	 * Retains only the elements in this Vector that are contained in the
	 * specified Collection.
	 * 使Vector仅包含collection中的元素.
	 * <p>
	 * In other words, removes from this Vector all
	 * of its elements that are not contained in the specified Collection.
	 * 换言之,移除Vector中不存在于指定集合collection中的所有元素.
	 *
	 * @param c a collection of elements to be retained in this Vector
	 *          (all other elements are removed)
	 *          需要被保留元素的集合(剩余其他的所有元素将被移除)
	 * @return true if this Vector changed as a result of the call
	 * 如果Vector发生了改变则返回true
	 * @throws ClassCastException   if the types of one or more elements
	 *                              in this vector are incompatible with the specified
	 *                              collection
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 *                              如果指定集合中有一个或更多个元素的类型与Vector的类型不兼容则抛出此异常
	 * @throws NullPointerException if this vector contains one or more null
	 *                              elements and the specified collection does not support null
	 *                              elements
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>),
	 *                              or if the specified collection is null
	 *                              如果Vector包含一个或更多个null元素且指定的collection不支持null元素或collection集合为null则抛出此异常
	 * @since 1.2
	 */
	public synchronized boolean retainAll( Collection<?> c ) {
		return super.retainAll( c );
	}

	/**
	 * Inserts all of the elements in the specified Collection into this
	 * Vector at the specified position.
	 * 将collection集合中的元素插入到vector中的特定位置.
	 * <p>
	 * Shifts the element currently at
	 * that position (if any) and any subsequent elements to the right
	 * (increases their indices).
	 * 先将该位置的元素顺序后移 集合大小个位置 然后执行插入
	 * The new elements will appear in the Vector
	 * in the order that they are returned by the specified Collection's
	 * iterator.
	 * 插入的顺序取决于collection迭代器的元素返回顺序
	 *
	 * @param index index at which to insert the first element from the
	 *              specified collection
	 *              指定被添加集合中第一个元素的插入位置,剩余元素依次插入
	 * @param c     elements to be inserted into this Vector
	 *              将要被添加到vector中的元素集合.
	 * @return {@code true} if this Vector changed as a result of the call
	 * 如果Vector发生了改变则返回true
	 * @throws ArrayIndexOutOfBoundsException if the index is out of range
	 *                                        ({@code index < 0 || index > size()})
	 *                                        越界检查
	 * @throws NullPointerException           if the specified collection is null
	 *                                        如果被添加的集合为null
	 * @since 1.2
	 */
	public synchronized boolean addAll( int index, Collection<? extends E> c ) {
		// fast-failed处理
		modCount++;
		// 越界检查
		if ( index < 0 || index > elementCount )
			throw new ArrayIndexOutOfBoundsException( index );
		// 将被添加的元素集合变为元素数组
		Object[] a = c.toArray();
		// 计算被添加元素的个数
		int numNew = a.length;
		// 保证vector中的对象数组 至少扩容到现有元素+新元素大小
		ensureCapacityHelper( elementCount + numNew );
		// 计算 需要被移动元素的个数
		int numMoved = elementCount - index;
		// 如果是在vector后面添加则不需要移动原有元素
		if ( numMoved > 0 )
			System.arraycopy( elementData, index, elementData, index + numNew,
					numMoved );
		// 执行插入操作
		System.arraycopy( a, 0, elementData, index, numNew );
		// 刷新现有元素数量
		elementCount += numNew;
		// 判断新添加的元素个数是否等于0
		return numNew != 0;
	}

	/**
	 * Compares the specified Object with this Vector for equality.
	 * 比较当前vector与传入对象是否相等.
	 * <p>
	 * Returns true if and only if the specified Object is also a List, both Lists
	 * have the same size, and all corresponding pairs of elements in the two
	 * Lists are <em>equal</em>.
	 * 当且仅当传入的obj是List接口的子类,且拥有相同大学,在相同位置有对应的相同的元素时 返回true
	 * <p>
	 * (Two elements {@code e1} and
	 * {@code e2} are <em>equal</em> if {@code (e1==null ? e2==null :
	 * e1.equals(e2))}.)
	 * <p>
	 * In other words, two Lists are defined to be
	 * equal if they contain the same elements in the same order.
	 * 换言之,两个List子集需要有相同的元素且顺序相同.
	 *
	 * @param o the Object to be compared for equality with this Vector 需要被与Vector进行比较的对象
	 * @return true if the specified Object is equal to this Vector 返回true时表明obj与当前Vector存储的元素相同且顺序相同.
	 */
	public synchronized boolean equals( Object o ) {
		return super.equals( o );
	}

	/**
	 * Returns the hash code value for this Vector.
	 * 返回当前vector的哈希编码
	 */
	public synchronized int hashCode() {
		return super.hashCode();
	}

	/**
	 * Returns a string representation of this Vector, containing
	 * the String representation of each element.
	 * 使用collection的toString方法进行重载
	 */
	public synchronized String toString() {
		return super.toString();
	}

	/**
	 * Returns a view of the portion of this List between fromIndex,
	 * inclusive, and toIndex, exclusive.
	 * 返回List的一个子集,[fromIndex,toIndex).
	 *
	 * (If fromIndex and toIndex are equal, the returned List is empty.)
	 * 如果fromIndex与toIndex相等则返回空集合.
	 *
	 * The returned List is backed by this
	 * List, so changes in the returned List are reflected in this List, and vice-versa.
	 * The returned List supports all of the optional List
	 * operations supported by this List.
	 * 返回的集合基于List,所以如果修改返回的子集将会影响原有集合,返回的集合支持本集合中的所有操作.
	 *
	 * <p>This method eliminates the need for explicit range operations (of the sort that commonly exist for arrays).
	 * 本方法需要明确的界限操作 (数组中通常存在的那种)
	 *
	 * Any operation that expects
	 * a List can be used as a range operation by operating on a subList view
	 * instead of a whole List.
	 * 处理一些操作时,有时期望操作子集从而代替操作整个集合.
	 *
	 * For example, the following idiom
	 * removes a range of elements from a List:
	 * 例子:如下的语句从list中移除一定范围的元素
	 * <pre>
	 *      list.subList(from, to).clear();
	 * </pre>
	 *
	 *
	 * Similar idioms may be constructed for indexOf and lastIndexOf,
	 * and all of the algorithms in the Collections class can be applied to a subList.
	 * 相似的,indexOf与lastIndexOf方法,所有collections类中的方法都可以被用在子集中.
	 *
	 * <p>The semantics of the List returned by this method become undefined if
	 * the backing list (i.e., this List) is <i>structurally modified</i> in
	 * any way other than via the returned List.
	 * 如果在返回子列表时,当前列表被结构化修改则返回的本方法的结果将变为不确定.
	 *
	 * (Structural modifications are those that change the size of the List, or otherwise perturb it in such
	 * a fashion that iterations in progress may yield incorrect results.)
	 * (结构上的修改是指改变列表的大小，或其他方式，遍历过程中可能产生不正确的结果.)
	 *
	 * @param fromIndex low endpoint (inclusive) of the subList 包含fromIndex元素,起始位置
	 * @param toIndex high endpoint (exclusive) of the subList 不包含toIndex元素,终止位置
	 * @return a view of the specified range within this List 返回一个子集
	 * @throws IndexOutOfBoundsException if an endpoint index value is out of range
	 *         {@code (fromIndex < 0 || toIndex > size)}
	 *         越界检查
	 * @throws IllegalArgumentException if the endpoint indices are out of order
	 *         {@code (fromIndex > toIndex)}
	 *         如果 fromIndex > toIndex 则抛出此异常
	 */
//	public synchronized List<E> subList(int fromIndex, int toIndex) {
//		return Collections.synchronizedList(super.subList(fromIndex, toIndex),this);
//	}

	/**
	 * Removes from this list all of the elements whose index is between
	 * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.
	 * 移除[fromIndex,toIndex)之间的元素.包含fromIndex不包含toIndex.
	 * <p>
	 * Shifts any succeeding elements to the left (reduces their index).
	 * 原有元素将会顺序迁移
	 * This call shortens the list by {@code (toIndex - fromIndex)} elements.
	 * 本操作将会将列表缩短toIndex - fromIndex个元素.
	 * (If {@code toIndex==fromIndex}, this operation has no effect.)
	 * 如果toIndex等于fromIndex,则本操作不生效.
	 */
	protected synchronized void removeRange( int fromIndex, int toIndex ) {
		// fast-failed处理
		modCount++;
		// 计算需要移动元素的个数
		int numMoved = elementCount - toIndex;
		// 将toIndex位置的元素包括此处元素及到末尾的元素向前移动numMoved个
		System.arraycopy( elementData, toIndex, elementData, fromIndex,
				numMoved );

		// Let gc do its work
		// 方便gc 计算新的数组为元素尾位置或者说新数组应该保留元素的个数 = 原有元素数量 - (被删除元素个数)
		int newElementCount = elementCount - (toIndex - fromIndex);
		// 循环从后向前置null
		while ( elementCount != newElementCount )
			elementData[--elementCount] = null;
	}

	/**
	 * Save the state of the {@code Vector} instance to a stream (that
	 * is, serialize it).
	 * 序列化方法 保存当前对象的状态
	 * This method performs synchronization to ensure the consistency
	 * of the serialized data.
	 * 本方法执行时是同步的以保证同步的数据一致性
	 */
	private void writeObject( java.io.ObjectOutputStream s )
			throws java.io.IOException {
		final java.io.ObjectOutputStream.PutField fields = s.putFields();
		final Object[] data;
		synchronized (this) {
			fields.put( "capacityIncrement", capacityIncrement );
			fields.put( "elementCount", elementCount );
			data = elementData.clone();
		}
		fields.put( "elementData", data );
		s.writeFields();
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper
	 * sequence), starting at the specified position in the list.
	 * 以适当的顺序返回list的index作为起始位置开始的集合迭代器
	 * <p>
	 * The specified index indicates the first element that would be
	 * returned by an initial call to {@link ListIterator#next next}.
	 * 返回第一个元素的操作通过.next方法触发
	 * <p>
	 * An initial call to {@link ListIterator#previous previous} would
	 * return the element with the specified index minus one.
	 * 调用.previous方法将会返回当前index-1的元素.
	 * <p>
	 * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
	 *
	 * @throws IndexOutOfBoundsException {@inheritDoc} 可能抛出数组越界异常
	 */
	public synchronized ListIterator<E> listIterator( int index ) {
		//越界检查
		if ( index < 0 || index > elementCount )
			throw new IndexOutOfBoundsException( "Index: " + index );
		//返回一个Vector的迭代器
		return new Vector.ListItr( index );
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper
	 * sequence).
	 * 以适当的顺序返回list的起始位置开始的集合迭代器
	 * <p>
	 * <p>
	 * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
	 *
	 * @see #listIterator(int)
	 */
	public synchronized ListIterator<E> listIterator() {
		return new Vector.ListItr( 0 );
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * 以适当的顺序返回一个迭代器
	 * <p>
	 * <p>The returned iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
	 *
	 * @return an iterator over the elements in this list in proper sequence 返回的迭代器元素顺序基于当前list集合
	 */
	public synchronized Iterator<E> iterator() {
		return new Vector.Itr();
	}

	/**
	 * An optimized version of AbstractList.Itr
	 * 一个AbstractList.Itr的优化版本
	 */
	private class Itr implements Iterator<E> {
		//下一个将要被返回元素的游标
		int cursor;       // index of next element to return
		//上一个返回元素的游标 如果等于-1则没有返回过元素或删除过元素
		int lastRet = -1; // index of last element returned; -1 if no such

		//fast-failed处理
		int expectedModCount = modCount;

		//判断是否有下一个元素
		public boolean hasNext() {
			// Racy but within spec, since modifications are checked
			// within or after synchronization in next/previous
			// 判断当前游标是否等于vector的元素数量
			return cursor != elementCount;
		}

		//获取下一个元素
		public E next() {
			//同步锁
			synchronized (Vector.this) {
				//fast-failed检查
				checkForComodification();
				//将调用前的下一元素游标赋值给i
				int i = cursor;
				//如果i大于等于元素数量则抛出异常
				if ( i >= elementCount )
					throw new NoSuchElementException();
				// 游标后移一位
				cursor = i + 1;
				// 获取当前元素且将当前元素index赋值给上一个元素index变量
				return elementData( lastRet = i );
			}
		}

		//移除上一个返回的元素
		public void remove() {
			//如果上一次没有返回元素则抛出异常
			if ( lastRet == -1 )
				throw new IllegalStateException();
			//同步锁
			synchronized (Vector.this) {
				//fast-failed检查
				checkForComodification();
				//调用该类的remove方法 传入调用前返回元素的index执行移除操作
				Vector.this.remove( lastRet );
				//fast-failed处理
				expectedModCount = modCount;
			}
			//将调用前上一个返回元素的index赋值给下一个元素游标 相当于双向链表去除一个节点
			cursor = lastRet;
			//将上一个元素置为-1
			lastRet = -1;
		}

		@Override
		public void forEachRemaining( Consumer<? super E> action ) {
			//判断执行action不为null 如果为null则抛出空指针异常
			Objects.requireNonNull( action );
			//同步锁
			synchronized (Vector.this) {
				// 缓存元素数量
				final int size = elementCount;
				// 获取下一个元素的游标index
				int i = cursor;
				// 判断如果下一个元素指针大于或等于当前vector元素大小 则不继续执行
				if ( i >= size ) {
					return;
				}
				// 获取元素数组
				@SuppressWarnings("unchecked")
				final E[] elementData = (E[]) Vector.this.elementData;
				// 判断下一个游标是否大于等于元素数组长度 fast-failed校验
				if ( i >= elementData.length ) {
					throw new ConcurrentModificationException();
				}
				//fast-failed处理 顺序后移遍历元素 对于每一个元素执行action所定义的操作
				while ( i != size && modCount == expectedModCount ) {
					action.accept( elementData[i++] );
				}
				// update once at end of iteration to reduce heap write traffic
				// 在迭代结束时统一更新一次相关计数变量以减少堆的写通信量
				cursor = i;
				lastRet = i - 1;
				checkForComodification();
			}
		}

		//fast-failed校验 当且仅当修改数量不等于
		final void checkForComodification() {
			//fast-failed处理
			if ( modCount != expectedModCount )
				throw new ConcurrentModificationException();
		}
	}

	/**
	 * An optimized version of AbstractList.ListItr
	 * 一个AbstractList.ListItr的优化版本
	 */
	final class ListItr extends Itr implements ListIterator<E> {
		//构造器
		ListItr( int index ) {
			super();
			cursor = index;
		}

		//判断是否有前一个元素
		public boolean hasPrevious() {
			return cursor != 0;
		}

		//判断是否有后一个元素
		public int nextIndex() {
			return cursor;
		}

		//获取前一个元素游标
		public int previousIndex() {
			return cursor - 1;
		}

		//获取前一个元素
		public E previous() {
			//同步锁
			synchronized (Vector.this) {
				//fast-failed check
				checkForComodification();
				//获取前一个元素游标
				int i = cursor - 1;
				//越界检查
				if ( i < 0 )
					throw new NoSuchElementException();
				//将前一个元素游标置给下一元素游标
				cursor = i;
				//返回上一个元素游标位置的元素并且将前一个元素游标置给上一个返回元素的游标变量
				return elementData( lastRet = i );
			}
		}

		//刷新上一个返回元素位置的元素值为新元素e
		public void set( E e ) {
			//判断是否返回过元素
			if ( lastRet == -1 )
				throw new IllegalStateException();
			//同步锁
			synchronized (Vector.this) {
				//fast-failed check
				checkForComodification();
				//将上一个元素返回位置的元素值设置为新元素e
				Vector.this.set( lastRet, e );
			}
		}

		//添加新元素e
		public void add( E e ) {
			//设置下一元素
			int i = cursor;
			synchronized (Vector.this) {
				//fast-failed check
				checkForComodification();
				//设置下一个元素位置的元素值为e
				Vector.this.add( i, e );
				//fast-failed处理
				expectedModCount = modCount;
			}
			//设置下一个元素游标值
			cursor = i + 1;
			//上一个元素游标设置为-1
			lastRet = -1;
		}
	}

	@Override
	public synchronized void forEach( Consumer<? super E> action ) {
		//为空校验
		Objects.requireNonNull( action );
		//fast-failed处理
		final int expectedModCount = modCount;
		//获取当前vector中的对象数组
		@SuppressWarnings("unchecked")
		final E[] elementData = (E[]) this.elementData;
		//获取当前vector中的对象大小
		final int elementCount = this.elementCount;
		//遍历对象数组 针对每个对象均执行action操作 并在每次执行的时候判断modCount是否更改过 如果更改过则在下一步抛出异常
		for ( int i = 0 ; modCount == expectedModCount && i < elementCount ; i++ ) {
			action.accept( elementData[i] );
		}
		//fast-failed处理
		if ( modCount != expectedModCount ) {
			throw new ConcurrentModificationException();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized boolean removeIf( Predicate<? super E> filter ) {
		//为空校验
		Objects.requireNonNull( filter );
		// figure out which elements are to be removed
		// any exception thrown from the filter predicate at this stage
		// will leave the collection unmodified
		// 移除元素变量初始化
		int removeCount = 0;
		// 缓存原有元素数量
		final int size = elementCount;
		// 生成需要删除的bitSet
		final BitSet removeSet = new BitSet( size );
		//fast-failed处理
		final int expectedModCount = modCount;
		//fast-failed处理
		//遍历数组 对于每一个元素 如果符合删除条件则放入bitSet中同时removeCount++
		for ( int i = 0 ; modCount == expectedModCount && i < size ; i++ ) {
			@SuppressWarnings("unchecked")
			final E element = (E) elementData[i];
			if ( filter.test( element ) ) {
				removeSet.set( i );
				removeCount++;
			}
		}
		//fast-failed处理
		//如果在遍历过程中vector发生了改变则抛出此异常
		if ( modCount != expectedModCount ) {
			throw new ConcurrentModificationException();
		}

		// shift surviving elements left over the spaces left by removed elements
		//判断是否有元素需要删除
		final boolean anyToRemove = removeCount > 0;
		if ( anyToRemove ) {
			//删除后的数量
			final int newSize = size - removeCount;
			//i为原数组中的index,j为需要删除元素的index
			for ( int i = 0, j = 0 ; (i < size) && (j < newSize) ; i++, j++ ) {
				i = removeSet.nextClearBit( i );
				elementData[j] = elementData[i];
			}
			//将以删除元素在原数组中下标位置开始之后的元素依次置null 方便gc
			for ( int k = newSize ; k < size ; k++ ) {
				elementData[k] = null;  // Let gc do its work
			}
			//刷新计数变量
			elementCount = newSize;
			//fast-failed处理
			if ( modCount != expectedModCount ) {
				throw new ConcurrentModificationException();
			}
			//fast-failed处理
			modCount++;
		}
		//返回是否有删除元素
		return anyToRemove;
	}

	@Override
	@SuppressWarnings("unchecked")
	public synchronized void replaceAll( UnaryOperator<E> operator ) {
		//为空校验
		Objects.requireNonNull( operator );
		//fast-failed处理
		//记录当前修改次数
		final int expectedModCount = modCount;
		//记录当前元素数量
		final int size = elementCount;
		//fast-failed处理
		//遍历元素数组 对于每个元素执行operator操作
		for ( int i = 0 ; modCount == expectedModCount && i < size ; i++ ) {
			elementData[i] = operator.apply( (E) elementData[i] );
		}
		//fast-failed处理
		if ( modCount != expectedModCount ) {
			throw new ConcurrentModificationException();
		}
		//fast-failed处理
		modCount++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized void sort( Comparator<? super E> c ) {
		//fast-failed处理
		final int expectedModCount = modCount;
		//排序
		Arrays.sort( (E[]) elementData, 0, elementCount, c );
		//fast-failed处理
		if ( modCount != expectedModCount ) {
			throw new ConcurrentModificationException();
		}
		//fast-failed处理
		modCount++;
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
		return new Vector.VectorSpliterator<>( this, null, 0, -1, 0 );
	}

	/**
	 * Similar to ArrayList Spliterator
	 * 分配器
	 */
	static final class VectorSpliterator<E> implements Spliterator<E> {
		//子vector列表
		private final Vector<E> list;
		private Object[] array;
		private int index; // current index, modified on advance/split
		private int fence; // -1 until used; then one past last index
		private int expectedModCount; // initialized when fence set

		/**
		 * Create new spliterator covering the given  range
		 */
		VectorSpliterator( Vector<E> list, Object[] array, int origin, int fence,
		                   int expectedModCount ) {
			this.list = list;
			this.array = array;
			this.index = origin;
			this.fence = fence;
			this.expectedModCount = expectedModCount;
		}

		private int getFence() { // initialize on first use
			int hi;
			if ( (hi = fence) < 0 ) {
				synchronized (list) {
					array = list.elementData;
					//fast-failed处理
					expectedModCount = list.modCount;
					hi = fence = list.elementCount;
				}
			}
			return hi;
		}

		public Spliterator<E> trySplit() {
			int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
			return (lo >= mid) ? null :
					new Vector.VectorSpliterator<E>( list, array, lo, index = mid,
							expectedModCount );
		}

		@SuppressWarnings("unchecked")
		public boolean tryAdvance( Consumer<? super E> action ) {
			int i;
			if ( action == null )
				throw new NullPointerException();
			if ( getFence() > (i = index) ) {
				index = i + 1;
				action.accept( (E) array[i] );
				//fast-failed处理
				if ( list.modCount != expectedModCount )
					throw new ConcurrentModificationException();
				return true;
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		public void forEachRemaining( Consumer<? super E> action ) {
			int i, hi; // hoist accesses and checks from loop
			Vector<E> lst;
			Object[] a;
			if ( action == null )
				throw new NullPointerException();
			if ( (lst = list) != null ) {
				if ( (hi = fence) < 0 ) {
					synchronized (lst) {
						//fast-failed处理
						expectedModCount = lst.modCount;
						a = array = lst.elementData;
						hi = fence = lst.elementCount;
					}
				} else
					a = array;
				if ( a != null && (i = index) >= 0 && (index = hi) <= a.length ) {
					while ( i < hi )
						action.accept( (E) a[i++] );
					//fast-failed处理
					if ( lst.modCount == expectedModCount )
						return;
				}
			}
			throw new ConcurrentModificationException();
		}

		//预计数量
		public long estimateSize() {
			return (long) (getFence() - index);
		}

		public int characteristics() {
			return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
		}
	}
}
