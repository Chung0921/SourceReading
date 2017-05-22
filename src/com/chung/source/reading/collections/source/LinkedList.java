package com.chung.source.reading.collections.source;

import java.util.*;
import java.util.function.Consumer;
//package java.util;

/**
 * Created by Chung.
 * Usage:
 * Description:
 * LinkedList插入、删除元素比较快，如果只要调整指针的指向那么时间复杂度是O(1),
 * 但是如果针对特定位置需要遍历时，时间复杂度是O(n)
 * Create dateTime: 17/5/17
 */

/**
 * Doubly-linked list implementation of the {@code List} and {@code Deque}
 * interfaces.
 * 双向链表的接口:List和Deque.
 * <p>
 * Implements all optional list operations, and permits all elements (including {@code null}).
 * 实现了List所有的可选方法,可以放入所有元素(包含null元素).
 * <p>
 * <p>
 * <p>All of the operations perform as could be expected for a doubly-linked list.
 * 对双链表的所有操作都可以预期执行.
 * <p>
 * Operations that index into the list will traverse the list from the beginning or the end, whichever is closer to the specified index.
 * 索引到列表中的操作将从开始或末尾遍历列表,以接近指定的索引位置.
 * <p>
 * <p>
 * <p><strong>Note that this implementation is not synchronized.
 * 注意:该类不是线程同步的.
 * <p>
 * If multiple threads access a linked list concurrently, and at least one of the threads modifies the list structurally, it <i>must</i> be synchronized externally.
 * 如果多个线程并发访问链接列表, 并且至少有一个线程在结构上修改列表,该列表必须在外部被同步.
 * <p>
 * (A structural modification is any operation that adds or deletes one or more elements;
 * (结构修改是指任何添加或删除一个或多个元素的操作;
 * <p>
 * merely setting the value of an element is not a structural modification.)
 * 仅仅修改元素的值不算事结构修改.)
 * <p>
 * This is typically accomplished by synchronizing on some object that naturally encapsulates the list.
 * 通常的解决方式是 通过使用某些对象来对列表进行同步.
 * <p>
 * <p>
 * If no such object exists, the list should be "wrapped" using the
 * {@link Collections#synchronizedList Collections.synchronizedList}
 * method.
 * 如果没有此列对象存在 则需要调用Collections#synchronizedList来完成对链表的同步操作
 * <p>
 * This is best done at creation time, to prevent accidental unsynchronized access to the list:
 * 最好在创建该列表的时候就防止意外地对列表的不同步访问:
 * <pre>
 *   List list = Collections.synchronizedList(new LinkedList(...));</pre>
 * <p>
 * <p>
 * <p>The iterators returned by this class's {@code iterator} and {@code listIterator} methods are <i>fail-fast</i>:
 * <p> 此类返回的迭代器 {@code iterator} 和 {@code listIterator} 方法返回的,均是 <i> fail-fast </i> 的:
 * <p>
 * if the list is structurally modified at any time after the iterator is created,
 * 如果该列表在创建迭代器后的任何时间结构上被修改,
 * <p>
 * in any way except through the Iterator's own {@code remove} or {@code add} methods,
 * 除通过迭代器自己的 {@code 删除} 或 {@code add} 方法外的任何方式尝试修改结构
 * <p>
 * the iterator will throw a {@link ConcurrentModificationException}.
 * 那么迭代器将会抛出 ConcurrentModificationException 异常
 * <p>
 * Thus, in the face of concurrent modification, the iterator fails quickly and cleanly, rather than
 * risking arbitrary, non-deterministic behavior at an undetermined time in the future.
 * 因此,面对并发修改,迭代器的fail-fast就比在未来不确定的时间点发生武断,冒险的行为好得多了.
 * <p>
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed as it is,
 * 请注意,不能保证迭代器的失效快速行为
 * <p>
 * generally speaking, impossible to make any hard guarantees in the presence of unsynchronized concurrent modification.
 * 一般来说,不可能在不同步的并发修改的存在下做出任何硬保证.
 * <p>
 * Fail-fast iterators throw {@code ConcurrentModificationException} on a best-effort basis.
 * Fail-fast迭代器抛出ConcurrentModificationException在best-effort基础上.
 * <p>
 * Therefore, it would be wrong to write a program that depended on this exception for its correctness:
 * 因此, 编写一个依赖于此异常的程序的正确性是错误的
 * <i>the fail-fast behavior of iterators should be used only to detect bugs.</i>
 * 该异常仅可以用作调试bug.
 * <p>
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <E> the type of elements held in this collection
 * @author Josh Bloch
 * @see List
 * @see java.util.ArrayList
 * @since 1.2
 */
public class LinkedList<E>
		extends AbstractSequentialList<E>
		implements List<E>, Deque<E>, Cloneable, java.io.Serializable {

	//链表长度
	transient int size = 0;

	/**
	 * Pointer to first node.
	 * Invariant: (first == null && last == null) ||
	 * (first.prev == null && first.item != null)
	 * <p>
	 * 头指针指向第一个元素
	 */
	transient LinkedList.Node<E> first;

	/**
	 * Pointer to last node.
	 * Invariant: (first == null && last == null) ||
	 * (last.next == null && last.item != null)
	 * 尾指针指向最后一个元素
	 */
	transient LinkedList.Node<E> last;

	/**
	 * Constructs an empty list.
	 * 构造一个空list
	 */
	public LinkedList() {
	}

	/**
	 * Constructs a list containing the elements of the specified
	 * collection, in the order they are returned by the collection's
	 * iterator.
	 * 通过一个collection列表构造一个linkedList,链表中的顺序与集合迭代器返回的顺序一致.
	 *
	 * @param c the collection whose elements are to be placed into this list
	 *          collection中所有的元素均会被添加到链表中
	 * @throws NullPointerException if the specified collection is null
	 *                              如果指定的collection为null则抛出空指针异常
	 */
	public LinkedList( Collection<? extends E> c ) {
		this();
		addAll( c );
	}

	/**
	 * Links e as first element.
	 * 将e元素连接到链表中作为第一个元素
	 */
	private void linkFirst( E e ) {
		//缓存原有头结点
		final LinkedList.Node<E> f = first;
		//创建一个包含元素e的新节点 前驱指向null 后继指向first引用
		final LinkedList.Node<E> newNode = new LinkedList.Node<>( null, e, f );
		//设置头结点
		first = newNode;
		//如果之前的头结点为空,那么说明链表还未添加过元素,尾节点就是头结点
		if ( f == null )
			last = newNode;
		else
			//如果之前的头结点不为空,即存在头结点,则将原有首节点的前一个节点为当前首节点
			f.prev = newNode;
		//链表长度+1
		size++;
		//修改次数+1
		modCount++;
	}

	/**
	 * Links e as last element.
	 * 将e元素连接到链表中作为最后一个元素
	 */
	void linkLast( E e ) {
		//缓存原有尾节点
		final LinkedList.Node<E> l = last;
		//创建一个包含元素e的新节点  前驱指向之前的尾节点 后继指向null
		final LinkedList.Node<E> newNode = new LinkedList.Node<>( l, e, null );
		//设置尾节点
		last = newNode;
		//如果之前的尾节点为null,说明链表暂时没有初始化则将该节点设置为头结点
		if ( l == null )
			first = newNode;
		else
			//如果存在尾节点,则将之前的尾节点的后继指向该新尾节点
			l.next = newNode;
		//链表长度+1
		size++;
		//修改次数+1
		modCount++;
	}

	/**
	 * Inserts element e before non-null Node succ.
	 * 在指定succ节点插入包含元素e的节点,succ不可为null
	 */
	void linkBefore( E e, LinkedList.Node<E> succ ) {
		// assert succ != null;
		//缓存原有succ的前驱节点
		final LinkedList.Node<E> pred = succ.prev;
		//创建一个新节点,包含元素e,且该节点前驱指向原有succ节点前驱,该节点后继指向succ
		final LinkedList.Node<E> newNode = new LinkedList.Node<>( pred, e, succ );
		//刷新succ的前驱指向为新插入的节点
		succ.prev = newNode;
		//如果原有succ的前驱为null,说明链表未插入过任何节点,则首节点为新节点
		if ( pred == null )
			first = newNode;
		else
			//否则将原有succ的前驱节点的后继指针指向新节点
			pred.next = newNode;
		//链表长度+1
		size++;
		//修改次数+1
		modCount++;
	}

	/**
	 * Unlinks non-null first node f.
	 * 删除首节点并返回删除前的首节点元素.
	 */
	private E unlinkFirst( LinkedList.Node<E> f ) {
		// assert f == first && f != null;
		//缓存首节点的值
		final E element = f.item;
		//将待删除节点的后继节点缓存
		final LinkedList.Node<E> next = f.next;
		//将待删除的节点元素置null 便于GC清理
		f.item = null;
		//将待删除的节点的后继节点置null 便于GC清理
		f.next = null; // help GC
		//将链表中的首节点值设置为f的原有后继节点,即原有首节点的下一节点现成为该链表中了新的首节点
		first = next;
		//如果不存在下一个节点 那么将尾节点也置为null (空表)
		if ( next == null )
			last = null;
		else
			//否则如果原有首节点存在下一个节点A,那么将A节点(新的首节点)的前驱置为null
			next.prev = null;
		//链表长度-1
		size--;
		//修改次数+1
		modCount++;
		return element;
	}

	/**
	 * Unlinks non-null last node l.
	 * 删除尾节点并返回之前尾节点的值
	 */
	private E unlinkLast( LinkedList.Node<E> l ) {
		// assert l == last && l != null;
		// 缓存之前尾节点的元素
		final E element = l.item;
		// 获取尾节点的前驱指向节点
		final LinkedList.Node<E> prev = l.prev;
		//将待删除的节点元素置null 便于GC清理
		l.item = null;
		//将待删除的节点的后继节点置null 便于GC清理
		l.prev = null; // help GC
		//将链表中的尾节点设置为l的原有前驱节点,即原有尾节点的前一个节点现成为了该链表中新的尾节点
		last = prev;
		//如果原有尾节点的前驱节点不存在,那么将头节点也置为null(空表)
		if ( prev == null )
			first = null;
		else
			//否则如果原有尾节点存在上一个节点A,那么将A节点(新的尾节点)的后继置为null
			prev.next = null;
		//链表长度-1
		size--;
		//修改次数+1
		modCount++;
		return element;
	}

	/**
	 * Unlinks non-null node x.
	 * 删除指定节点并返回该节点的元素值
	 */
	E unlink( LinkedList.Node<E> x ) {
		// assert x != null;
		//获取该将要被删除节点的元素
		final E element = x.item;
		//将要被删除节点的后继节点缓存
		final LinkedList.Node<E> next = x.next;
		//将要被删除节点的前驱节点缓存
		final LinkedList.Node<E> prev = x.prev;

		//(如当前节点为首节点)如果该要被删除节点的前驱为null,则将该节点的后继节点直接赋值给链表的首节点
		if ( prev == null ) {
			first = next;
		} else {
			//否则将要被删除节点x的前驱节点赋值给该要被删除节点x的前一个节点的前驱指针 即将该节点跳过,指针越过此节点
			prev.next = next;
			//并将要被删除的节点x的前驱指针置为null 方便gc回收
			x.prev = null;
		}
		//(如当前节点为尾节点)如果该要被删除节点的后继为null,则将该节点的前驱节点直接赋值给链表的尾节点
		if ( next == null ) {
			last = prev;
		} else {
			//否则将要被删除的节点x的前驱节点赋值给该要被删除节点x的前一个节点的前驱指针 即将该节点跳过,指针越过此节点
			next.prev = prev;
			//并将要被删除的节点x的后继指针置为null 方便gc回收
			x.next = null;
		}
		//将要被删除的节点x的元素置为null 方便gc回收
		x.item = null;
		//链表长度-1
		size--;
		//修改次数+1
		modCount++;
		return element;
	}

	/**
	 * Returns the first element in this list.
	 * 返回链表中的头结点
	 *
	 * @return the first element in this list 返回链表中的第一个节点
	 * @throws NoSuchElementException if this list is empty  如果该链表首节点为null则抛出此异常
	 */
	public E getFirst() {
		//得到头节点
		final LinkedList.Node<E> f = first;
		//如果为空，抛出异常
		if ( f == null )
			throw new NoSuchElementException();
		//返回头结点的元素
		return f.item;
	}

	/**
	 * Returns the last element in this list.
	 * 返回链表中的尾节点
	 *
	 * @return the last element in this list 返回链表中的尾节点
	 * @throws NoSuchElementException if this list is empty如果该链表尾节点为null则抛出此异常
	 */
	public E getLast() {
		//得到尾节点
		final LinkedList.Node<E> l = last;
		//如果为空，抛出异常
		if ( l == null )
			throw new NoSuchElementException();
		return l.item;
	}

	/**
	 * Removes and returns the first element from this list.
	 * 从链表中移除首节点并返回其元素的值
	 *
	 * @return the first element from this list 首节点元素的值
	 * @throws NoSuchElementException if this list is empty 如果链表为null时抛出此异常
	 */
	public E removeFirst() {
		//得到第一节点
		final LinkedList.Node<E> f = first;
		//如果为空，抛出异常
		if ( f == null )
			throw new NoSuchElementException();
		//从头删除首节点
		return unlinkFirst( f );
	}

	/**
	 * Removes and returns the last element from this list.
	 * 从链表中移除尾节点并返回该节点的元素
	 *
	 * @return the last element from this list 返回尾节点的元素值
	 * @throws NoSuchElementException if this list is empty 如果链表为null时抛出此异常
	 */
	public E removeLast() {
		final LinkedList.Node<E> l = last;
		//如果为空，抛出异常
		if ( l == null )
			throw new NoSuchElementException();
		//从后删除尾节点
		return unlinkLast( l );
	}

	/**
	 * Inserts the specified element at the beginning of this list.
	 * 添加一个指定的元素放到list的开头
	 *
	 * @param e the element to add 需要被添加的元素
	 */
	public void addFirst( E e ) {
		//从链表开头添加一个节点
		linkFirst( e );
	}

	/**
	 * Appends the specified element to the end of this list.
	 * 在链表结尾添加一个指定的元素
	 * <p>
	 * <p>This method is equivalent to {@link #add}. 与add方法相同
	 *
	 * @param e the element to add 将要被添加的元素
	 */
	public void addLast( E e ) {
		linkLast( e );
	}

	/**
	 * Returns {@code true} if this list contains the specified element.
	 * 如果链表包含特定元素则返回true
	 * <p>
	 * More formally, returns {@code true} if and only if this list contains
	 * at least one element {@code e} such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 * 更正式的说,当且仅当该元素与list中某个对象相同时才返回true
	 *
	 * @param o element whose presence in this list is to be tested 需要被测试的人
	 * @return {@code true} if this list contains the specified element 如果list中包含此元素
	 */
	public boolean contains( Object o ) {
		return indexOf( o ) != -1;
	}

	/**
	 * Returns the number of elements in this list.
	 * 返回链表中元素的数量
	 *
	 * @return the number of elements in this list
	 */
	public int size() {
		return size;
	}

	/**
	 * Appends the specified element to the end of this list.
	 * 将指定元素添加到链表尾部
	 * <p>
	 * <p>This method is equivalent to {@link #addLast}. 与addLast相同
	 *
	 * @param e element to be appended to this list 将要被添加的元素
	 * @return {@code true} (as specified by {@link Collection#add})
	 */
	public boolean add( E e ) {
		linkLast( e );
		return true;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if it is present.
	 * 从链表中移除指定元素(从第一次出现的位置)
	 * <p>
	 * If this list does not contain the element, it is unchanged.
	 * 如果list不包含此元素,则该链表不变.
	 * <p>
	 * More formally, removes the element with the lowest index
	 * {@code i} such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
	 * (if such an element exists).
	 * 更正式的说,如果该元素存在于链表中,那么从最低的index处将其移除
	 * Returns {@code true} if this list
	 * contained the specified element (or equivalently, if this list
	 * changed as a result of the call).
	 *
	 * @param o element to be removed from this list, if present 如果存在,则从list中移除该元素
	 * @return {@code true} if this list contained the specified element 是否包含指定的元素
	 */
	public boolean remove( Object o ) {
		if ( o == null ) {
			for ( LinkedList.Node<E> x = first ; x != null ; x = x.next ) {
				if ( x.item == null ) {
					unlink( x );
					return true;
				}
			}
		} else {
			for ( LinkedList.Node<E> x = first ; x != null ; x = x.next ) {
				if ( o.equals( x.item ) ) {
					unlink( x );
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Appends all of the elements in the specified collection to the end of
	 * this list, in the order that they are returned by the specified collection's iterator.
	 * 在list后面追加collection中的所有元素,链表中的顺序取决于该元素在collection中的顺序.
	 * <p>
	 * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
	 * 如果在执行该操作时,链表发生结构上的改变,则结果不确定.
	 * <p>
	 * (Note that this will occur if the specified collection is
	 * this list, and it's nonempty.)
	 * 注意: 这将会在指定的collection非空时发生.
	 *
	 * @param c collection containing elements to be added to this list 包含被添加元素的集合
	 * @return {@code true} if this list changed as a result of the call 链表是否发生了改变
	 * @throws NullPointerException if the specified collection is null 如果指定的collection为null
	 */
	public boolean addAll( Collection<? extends E> c ) {
		//size表示最后一个位置 传入size指定了从链表的最后一个元素位置开始追加
		return addAll( size, c );
	}

	/**
	 * Inserts all of the elements in the specified collection into this
	 * list, starting at the specified position.
	 * 从指定位置插入collection中的元素.
	 * Shifts the element
	 * currently at that position (if any) and any subsequent elements to
	 * the right (increases their indices).
	 * 将该位置起始的元素右移(添加他们的index)
	 * The new elements will appear
	 * in the list in the order that they are returned by the
	 * specified collection's iterator.
	 * 在链表中的顺序与collection中的顺序一致.
	 *
	 * @param index index at which to insert the first element
	 *              from the specified collection
	 *              index指定第一个插入元素后的位置
	 * @param c     collection containing elements to be added to this list
	 *              需要被添加到list中的元素集合
	 * @return {@code true} if this list changed as a result of the call
	 * 只要有一次添加就返回true
	 * @throws IndexOutOfBoundsException {@inheritDoc} 数组越界异常
	 * @throws NullPointerException      if the specified collection is null
	 *                                   如果指定的collection集合为null则抛出空指针异常
	 */
	public boolean addAll( int index, Collection<? extends E> c ) {
		//检查索引是否正确（0<=index<=size）
		checkPositionIndex( index );

		//集合转换数组
		Object[] a = c.toArray();
		//得到元素个数
		int numNew = a.length;
		//如果没有元素要添加则返回false
		if ( numNew == 0 )
			return false;

		LinkedList.Node<E> pred, succ;
		//如果从结尾开始,将当前节点的后一节点初始化为null,前一个节点为尾节点.
		if ( index == size ) {
			succ = null;
			pred = last;
		} else {
			//如果不是从结尾开始的,则获取index处的节点,缓存原有位置的节点,前一个节点为要添加的节点的前一个节点.[获取该处原有节点A及其前驱节点B]
			succ = node( index );
			pred = succ.prev;
		}
		//遍历数组并添加到列表中
		for ( Object o : a ) {
			@SuppressWarnings("unchecked") E e = (E) o;
			LinkedList.Node<E> newNode = new LinkedList.Node<>( pred, e, null );
			//如果前驱节点为null,则新添加的节点为首节点
			if ( pred == null )
				first = newNode;
			else
				//否则,如果存在前驱节点,则将前驱节点的后继指针指向新节点
				pred.next = newNode;
			//将新节点缓存,在下一次循环中作为下一个新节点的前驱节点
			pred = newNode;
		}

		//如果添加前的后继节点为null,则是从最后一个节点开始添加的,那么本次添加的最后一个节点应为新的尾节点
		if ( succ == null ) {
			//将本次添加的最后一个节点地址赋值给链表的尾节点
			last = pred;
		} else {
			//否则是从链表中间添加的,那么将最后一次添加的节点的后继指针 指向原有插入位置之后的那个节点A
			pred.next = succ;
			//将原有后续节点A的前驱指向本次添加的节点.
			succ.prev = pred;
		}
		//刷新链表长度及操作次数
		size += numNew;
		modCount++;
		return true;
	}

	/**
	 * Removes all of the elements from this list.
	 * The list will be empty after this call returns.
	 * 从列表中移除所有元素,当返回时,链表为空
	 */
	public void clear() {
		// Clearing all of the links between nodes is "unnecessary", but:
		// 虽然打破所有指针是"不必要的",但是将会帮助gc回收对象,
		// - helps a generational GC if the discarded nodes inhabit
		//   more than one generation
		// - is sure to free memory even if there is a reachable Iterator
		for ( LinkedList.Node<E> x = first ; x != null ; ) {
			LinkedList.Node<E> next = x.next;
			x.item = null;
			x.next = null;
			x.prev = null;
			x = next;
		}
		//链表置空表
		first = last = null;
		size = 0;
		modCount++;
	}


	// Positional Access Operations

	/**
	 * Returns the element at the specified position in this list.
	 * 获取指定索引的节点的值
	 *
	 * @param index index of the element to return 返回该index对应的元素值
	 * @return the element at the specified position in this list 在list中特定位置的值
	 * @throws IndexOutOfBoundsException {@inheritDoc} 数组越界异常
	 */
	public E get( int index ) {
		checkElementIndex( index );
		return node( index ).item;
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 * 修改指定索引的值并返回之前的值
	 *
	 * @param index   index of the element to replace 需要被修改值的元素index
	 * @param element element to be stored at the specified position 更新后的值
	 * @return the element previously at the specified position 先前元素的值
	 * @throws IndexOutOfBoundsException {@inheritDoc} 数组越界异常
	 */
	public E set( int index, E element ) {
		checkElementIndex( index );
		LinkedList.Node<E> x = node( index );
		E oldVal = x.item;
		x.item = element;
		return oldVal;
	}

	/**
	 * Inserts the specified element at the specified position in this list.
	 * 在指定位置后面添加元素
	 * <p>
	 * Shifts the element currently at that position (if any) and any
	 * subsequent elements to the right (adds one to their indices).
	 * 将后面的元素顺序右移1位
	 *
	 * @param index   index at which the specified element is to be inserted 指定新添加的元素需要添加在哪个index
	 * @param element element to be inserted 需要被添加的元素
	 * @throws IndexOutOfBoundsException {@inheritDoc} 数组越界异常
	 */
	public void add( int index, E element ) {
		checkPositionIndex( index );

		//如果指定位置为最后一个节点,则添加到链表最后
		if ( index == size )
			linkLast( element );
		else
			//如果不是最后,则插入到指定位置index原有元素之前(插入后新添加的元素index等于传入的index,原有index位置的元素顺序后移1位)
			linkBefore( element, node( index ) );
	}

	/**
	 * Removes the element at the specified position in this list.
	 * 移除特定索引位置的元素
	 * Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * 将该位置后的元素左移
	 * Returns the element that was removed from the list.
	 * 返回本次被移除的元素
	 *
	 * @param index the index of the element to be removed 需要被移除元素的索引
	 * @return the element previously at the specified position 先前此index位置的元素
	 * @throws IndexOutOfBoundsException {@inheritDoc} 数组越界异常
	 */
	public E remove( int index ) {
		checkElementIndex( index );
		return unlink( node( index ) );
	}

	/**
	 * Tells if the argument is the index of an existing element.
	 * 检查索引是否超出范围，因为元素索引是0~size-1的，所以index必须满足0<=index<size
	 */
	private boolean isElementIndex( int index ) {
		return index >= 0 && index < size;
	}

	/**
	 * Tells if the argument is the index of a valid position for an
	 * iterator or an add operation.
	 * 检查位置是否超出范围，index必须在0~size之间（含），如果超出，返回false
	 */
	private boolean isPositionIndex( int index ) {
		return index >= 0 && index <= size;
	}

	/**
	 * Constructs an IndexOutOfBoundsException detail message.
	 * Of the many possible refactorings of the error handling code,
	 * this "outlining" performs best with both server and client VMs.
	 * 生成异常详情
	 */
	private String outOfBoundsMsg( int index ) {
		return "Index: " + index + ", Size: " + size;
	}

	//检查元素索引是否超出范围，若已超出，就抛出异常
	private void checkElementIndex( int index ) {
		if ( !isElementIndex( index ) )
			throw new IndexOutOfBoundsException( outOfBoundsMsg( index ) );
	}

	//检查位置是否超出范围，若已超出，就抛出异常
	private void checkPositionIndex( int index ) {
		if ( !isPositionIndex( index ) )
			throw new IndexOutOfBoundsException( outOfBoundsMsg( index ) );
	}

	/**
	 * Returns the (non-null) Node at the specified element index.
	 * 根据指定的元素index返回一个非空节点
	 */
	LinkedList.Node<E> node( int index ) {
		// assert isElementIndex(index);
		//如果位置索引小于列表长度的一半(或一半减一)，从前面开始遍历；否则，从后面开始遍历
		if ( index < (size >> 1) ) {
			LinkedList.Node<E> x = first;
			for ( int i = 0 ; i < index ; i++ )
				x = x.next;
			return x;
		} else {
			LinkedList.Node<E> x = last;
			for ( int i = size - 1 ; i > index ; i-- )
				x = x.prev;
			return x;
		}
	}

	// Search Operations 搜索操作

	/**
	 * Returns the index of the first occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 * 返回元素第一次出现的index位置,如果返回-1则该链表不包含此元素.
	 * <p>
	 * More formally, returns the lowest index {@code i} such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 * 更正式的说,返回的index是第一次出现时的位置(最小index返回)
	 *
	 * @param o element to search for 需要被找寻的元素对象
	 * @return the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element
	 * 返回index位置,不存在返回-1
	 * <p>
	 * 获取指定元素从first开始的索引位置，不存在就返回-1 不能按条件双向找了，所以通常根据索引获得元素的速度比通过元素获得索引的速度快
	 */
	public int indexOf( Object o ) {
		int index = 0;
		if ( o == null ) {
			for ( LinkedList.Node<E> x = first ; x != null ; x = x.next ) {
				if ( x.item == null )
					return index;
				index++;
			}
		} else {
			for ( LinkedList.Node<E> x = first ; x != null ; x = x.next ) {
				if ( o.equals( x.item ) )
					return index;
				index++;
			}
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element
	 * in this list, or -1 if this list does not contain the element.
	 * 返回该元素最后一次出现的index位置,如果返回-1则该链表不包含此元素
	 * <p>
	 * More formally, returns the highest index {@code i} such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 * 更正式的说,返回的index是最后一次出现时的位置(最大index返回)
	 *
	 * @param o element to search for 需要被找寻的元素对象
	 * @return the index of the last occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element
	 * 返回index位置,不存在返回-1
	 */
	public int lastIndexOf( Object o ) {
		int index = size;
		if ( o == null ) {
			for ( LinkedList.Node<E> x = last ; x != null ; x = x.prev ) {
				index--;
				if ( x.item == null )
					return index;
			}
		} else {
			for ( LinkedList.Node<E> x = last ; x != null ; x = x.prev ) {
				index--;
				if ( o.equals( x.item ) )
					return index;
			}
		}
		return -1;
	}

	// Queue operations. 提供普通队列和双向队列的功能，当然，也可以实现栈，FIFO，FILO,具体看怎么调用

	/**
	 * Retrieves, but does not remove, the head (first element) of this list.
	 * 返回链表中的头结点,不会移动元素,仅仅是查询
	 *
	 * @return the head of this list, or {@code null} if this list is empty 返回头结点的元素,返回null则链表为空表
	 * @since 1.5
	 */
	public E peek() {
		final LinkedList.Node<E> f = first;
		return (f == null) ? null : f.item;
	}

	/**
	 * Retrieves, but does not remove, the head (first element) of this list.
	 * 返回链表中的头结点,不会移动元素,仅仅是查询
	 *
	 * @return the head of this list 链表中的头一个元素值
	 * @throws NoSuchElementException if this list is empty 如果头结点为null或链表为null则抛出此异常
	 * @since 1.5
	 */
	public E element() {
		return getFirst();
	}

	/**
	 * Retrieves and removes the head (first element) of this list.
	 * 出队（从前端），如果不存在会返回null，存在的话会返回值并移除这个元素（节点）
	 *
	 * @return the head of this list, or {@code null} if this list is empty 返回头结点元素值,如果不存在则返回null
	 * @since 1.5
	 */
	public E poll() {
		final LinkedList.Node<E> f = first;
		return (f == null) ? null : unlinkFirst( f );
	}

	/**
	 * Retrieves and removes the head (first element) of this list.
	 * 出队（从前端），如果不存在会抛出异常而不是返回null，存在的话会返回值并移除这个元素（节点）
	 *
	 * @return the head of this list 返回头结点元素值
	 * @throws NoSuchElementException if this list is empty 不存在则抛出异常
	 * @since 1.5
	 */
	public E remove() {
		return removeFirst();
	}

	/**
	 * Adds the specified element as the tail (last element) of this list.
	 * 添加一个指定的元素作为尾节点
	 *
	 * @param e the element to add 需要被添加的元素
	 * @return {@code true} (as specified by {@link Queue#offer})
	 * @since 1.5
	 */
	public boolean offer( E e ) {
		return add( e );
	}

	// Deque operations 队列操作

	/**
	 * Inserts the specified element at the front of this list.
	 * 入队(从前端)
	 *
	 * @param e the element to insert 需要被添加的元素
	 * @return {@code true} (as specified by {@link Deque#offerFirst})
	 * @since 1.6
	 */
	public boolean offerFirst( E e ) {
		addFirst( e );
		return true;
	}

	/**
	 * Inserts the specified element at the end of this list.
	 * 入队(从后端)
	 *
	 * @param e the element to insert 需要被添加的元素
	 * @return {@code true} (as specified by {@link Deque#offerLast})
	 * @since 1.6
	 */
	public boolean offerLast( E e ) {
		//same to linkLast
		addLast( e );
		return true;
	}

	/**
	 * Retrieves, but does not remove, the first element of this list,
	 * or returns {@code null} if this list is empty.
	 * 出队（从前端），获得第一个元素，不存在会返回null，不会删除元素（节点）
	 *
	 * @return the first element of this list, or {@code null}
	 * if this list is empty
	 * 返回第一个元素
	 * @since 1.6
	 */
	public E peekFirst() {
		final LinkedList.Node<E> f = first;
		return (f == null) ? null : f.item;
	}

	/**
	 * Retrieves, but does not remove, the last element of this list,
	 * or returns {@code null} if this list is empty.
	 * 出队（从后端），获得最后一个元素，不存在会返回null，不会删除元素（节点）
	 *
	 * @return the last element of this list, or {@code null}
	 * if this list is empty
	 * 返回最后一个元素
	 * @since 1.6
	 */
	public E peekLast() {
		final LinkedList.Node<E> l = last;
		return (l == null) ? null : l.item;
	}

	/**
	 * Retrieves and removes the first element of this list,
	 * or returns {@code null} if this list is empty.
	 * 出队（从前端），获得第一个元素，不存在会返回null，会删除元素（节点）
	 *
	 * @return the first element of this list, or {@code null} if
	 * this list is empty
	 * 返回头结点元素,返回null则链表为null
	 * @since 1.6
	 */
	public E pollFirst() {
		final LinkedList.Node<E> f = first;
		return (f == null) ? null : unlinkFirst( f );
	}

	/**
	 * Retrieves and removes the last element of this list,
	 * or returns {@code null} if this list is empty.
	 * 出队（从后端），获得最后一个元素，不存在会返回null，会删除元素（节点）
	 *
	 * @return the last element of this list, or {@code null} if
	 * this list is empty
	 * 返回头结点元素,返回null则链表为null
	 * @since 1.6
	 */
	public E pollLast() {
		final LinkedList.Node<E> l = last;
		return (l == null) ? null : unlinkLast( l );
	}

	/**
	 * Pushes an element onto the stack represented by this list.  In other
	 * words, inserts the element at the front of this list.
	 * 入栈，从前面添加
	 * <p>
	 * <p>
	 * <p>This method is equivalent to {@link #addFirst}.
	 *
	 * @param e the element to push 被压栈的元素
	 * @since 1.6
	 */
	public void push( E e ) {
		addFirst( e );
	}

	/**
	 * Pops an element from the stack represented by this list.  In other
	 * words, removes and returns the first element of this list.
	 * <p>
	 * <p>This method is equivalent to {@link #removeFirst()}.
	 * 出栈，返回栈顶元素，从前面移除（会删除）
	 *
	 * @return the element at the front of this list (which is the top
	 * of the stack represented by this list) 被出栈的元素
	 * @throws NoSuchElementException if this list is empty 如果空表则抛磁异常
	 * @since 1.6
	 */
	public E pop() {
		return removeFirst();
	}

	/**
	 * Removes the first occurrence of the specified element in this
	 * list (when traversing the list from head to tail).  If the list
	 * does not contain the element, it is unchanged.
	 * 从链表中移除指定元素,从头到尾(从第一次出现的位置).
	 * 如果链表不包含此元素则不变
	 *
	 * @param o element to be removed from this list, if present
	 * @return {@code true} if the list contained the specified element
	 * @since 1.6
	 */
	public boolean removeFirstOccurrence( Object o ) {
		return remove( o );
	}

	/**
	 * Removes the last occurrence of the specified element in this
	 * list (when traversing the list from head to tail).  If the list
	 * does not contain the element, it is unchanged.
	 * 从链表中移除指定元素,从尾到头(从第一次出现的位置).
	 * 如果链表不包含此元素则不变
	 *
	 * @param o element to be removed from this list, if present
	 * @return {@code true} if the list contained the specified element
	 * @since 1.6
	 */
	public boolean removeLastOccurrence( Object o ) {
		if ( o == null ) {
			for ( LinkedList.Node<E> x = last ; x != null ; x = x.prev ) {
				if ( x.item == null ) {
					unlink( x );
					return true;
				}
			}
		} else {
			for ( LinkedList.Node<E> x = last ; x != null ; x = x.prev ) {
				if ( o.equals( x.item ) ) {
					unlink( x );
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns a list-iterator of the elements in this list (in proper
	 * sequence), starting at the specified position in the list.
	 * 返回一个从指定位置开始的list迭代器.
	 *
	 * Obeys the general contract of {@code List.listIterator(int)}.<p>
	 * <p>
	 * 和list迭代器的一般规则相同
	 * The list-iterator is <i>fail-fast</i>: if the list is structurally
	 * modified at any time after the Iterator is created, in any way except
	 * through the list-iterator's own {@code remove} or {@code add}
	 * methods, the list-iterator will throw a
	 * {@code ConcurrentModificationException}.
	 * 该迭代器是fail-fast的:如果在迭代器创建后的任意时刻,链表的结构被改变了,除非调用迭代器本身方法:remove或add方法,其余将会抛出ConcurrentModificationException异常.
	 *
	 * Thus, in the face of
	 * concurrent modification, the iterator fails quickly and cleanly, rather
	 * than risking arbitrary, non-deterministic behavior at an undetermined
	 * time in the future.
	 *
	 *
	 * @param index index of the first element to be returned from the
	 *              list-iterator (by a call to {@code next})
	 *              返回第一个元素的位置
	 * @return a ListIterator of the elements in this list (in proper
	 * sequence), starting at the specified position in the list
	 *          返回在list中的迭代器,从指定index位置开始
	 * @throws IndexOutOfBoundsException {@inheritDoc} 数组越界异常
	 * @see List#listIterator(int)
	 */
	public ListIterator<E> listIterator( int index ) {
		checkPositionIndex( index );
		return new LinkedList.ListItr( index );
	}

	//链表迭代器
	private class ListItr implements ListIterator<E> {
		//缓存上一次返回的节点引用
		private LinkedList.Node<E> lastReturned;
		//缓存下一次将要返回的节点引用
		private LinkedList.Node<E> next;
		//缓存下一次将要返回的节点index
		private int nextIndex;
		//缓存修改次数
		private int expectedModCount = modCount;


		/**
		 * 从index处返回后续链表的迭代器
		 * @param index 索引地址
		 */
		ListItr( int index ) {
			// assert isPositionIndex(index);
			next = (index == size) ? null : node( index );
			nextIndex = index;
		}

		//判断如果下一次index游标小于链表长度,则还有元素未取到,返回真
		public boolean hasNext() {
			return nextIndex < size;
		}

		//获取下一个节点的元素
		public E next() {
			//判断是否越界
			checkForComodification();
			//判断后续是否还有元素
			if ( !hasNext() )
				throw new NoSuchElementException();
			//将本次返回的元素节点缓存作为上一次返回的节点
			lastReturned = next;
			//将下一个元素节点指针后移一位
			next = next.next;
			//下一次将要返回的节点index计数变量++
			nextIndex++;
			//返回本节点元素
			return lastReturned.item;
		}

		//根据下一个元素index是否大于0判断是否有前一个元素
		public boolean hasPrevious() {
			return nextIndex > 0;
		}

		//获取上一个节点的元素
		public E previous() {
			//越界检查
			checkForComodification();
			//是否包含上一个节点
			if ( !hasPrevious() )
				throw new NoSuchElementException();
			//如果下一个元素为null则当前节点为尾节点,lastReturned&next 赋值尾节点.否则赋值为当前节点的前驱节点
			lastReturned = next = (next == null) ? last : next.prev;
			//下一元素游标--
			nextIndex--;
			//返回当前节点元素
			return lastReturned.item;
		}

		//返回下一节点index指针
		public int nextIndex() {
			return nextIndex;
		}

		//返回上一节点index指针
		public int previousIndex() {
			return nextIndex - 1;
		}

		//从链表中移除元素
		public void remove() {
			//越界检查
			checkForComodification();
			//如果上一元素返回null则找不到被移除的节点(例如:返回空表时)
			if ( lastReturned == null )
				throw new IllegalStateException();
			//缓存要移除节点的后继指针
			LinkedList.Node<E> lastNext = lastReturned.next;
			//执行移除操作
			unlink( lastReturned );
			//比较 上一个返回的节点是否等于下一次要返回的节点
			if ( next == lastReturned )
				//如果等于则下一次返回移除节点的后继节点
				next = lastNext;
			else
				//否则下一次返回元素的index值--
				nextIndex--;
			//上一次返回的元素置空
			lastReturned = null;
			//修改次数++
			expectedModCount++;
		}

		//设置元素值给本次返回的index节点
		public void set( E e ) {
			if ( lastReturned == null )
				throw new IllegalStateException();
			//fast-fail校验
			checkForComodification();
			//设置新的元素值
			lastReturned.item = e;
		}

		//添加一个新的元素
		public void add( E e ) {
			checkForComodification();
			//设置上一次返回节点为null
			lastReturned = null;
			//如果下一个元素为null,则在链表末尾添加该元素e的新节点
			if ( next == null )
				linkLast( e );
			else
				//否则将e插入到当前节点与下一个节点中间
				linkBefore( e, next );
			//下一个index++
			nextIndex++;
			//修改次数++
			expectedModCount++;
		}

		//java8
		public void forEachRemaining( Consumer<? super E> action ) {
			Objects.requireNonNull( action );
			while ( modCount == expectedModCount && nextIndex < size ) {
				action.accept( next.item );
				lastReturned = next;
				next = next.next;
				nextIndex++;
			}
			checkForComodification();
		}

		//越界检查
		final void checkForComodification() {
			//fast-fail throw ConcurrentModificationException()
			if ( modCount != expectedModCount )
				throw new ConcurrentModificationException();
		}
	}

	//节点的数据结构，包含前后节点的引用和当前节点
	private static class Node<E> {
		//当前节点元素
		E item;
		//当前节点的后继节点
		LinkedList.Node<E> next;
		//当前节点的前驱节点
		LinkedList.Node<E> prev;

		Node( LinkedList.Node<E> prev, E element, LinkedList.Node<E> next ) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	/**
	 * 返回迭代器
	 *
	 * @since 1.6
	 */
	public Iterator<E> descendingIterator() {
		return new LinkedList.DescendingIterator();
	}

	/**
	 * Adapter to provide descending iterators via ListItr.previous
	 * 因为采用链表实现，所以迭代器很简单,反序的迭代器
	 */
	private class DescendingIterator implements Iterator<E> {
		//缓存正序迭代器
		private final LinkedList.ListItr itr = new LinkedList.ListItr( size() );

		//反序判断之前节点是否为null即判断正序时的上一个节点是否为null
		public boolean hasNext() {
			return itr.hasPrevious();
		}

		//反序返回之前节点即返回正序时的上一个节点
		public E next() {
//			源码为:
//          return itr.previous();
			return (E) itr.previous();
		}

		//迭代器删除元素
		public void remove() {
			itr.remove();
		}
	}

	@SuppressWarnings("unchecked")
	private LinkedList<E> superClone() {
		try {
			return (LinkedList<E>) super.clone();
		} catch ( CloneNotSupportedException e ) {
			throw new InternalError( e );
		}
	}

	/**
	 * Returns a shallow copy of this {@code LinkedList}.
	 * 返回一个链表的拷贝
	 *
	 * (The elements themselves are not cloned.)
	 * 链表中的元素并没有拷贝(没有深度复制,修改返回拷贝链表中的元素将直接影响原有链表中对应位置的元素对象)
	 *
	 * @return a shallow copy of this {@code LinkedList} instance
	 */
	public Object clone() {
		LinkedList<E> clone = superClone();

		// Put clone into "virgin" state
		// 将拷贝后的新链表恢复初始化状态
		clone.first = clone.last = null;
		clone.size = 0;
		clone.modCount = 0;

		// Initialize clone with our elements
		// 遍历添加元素对象的引用 x指向null时,为尾节点,循环停止
		for ( LinkedList.Node<E> x = first ; x != null ; x = x.next )
			clone.add( x.item );

		return clone;
	}

	/**
	 * Returns an array containing all of the elements in this list
	 * in proper sequence (from first to last element).
	 * 返回一个包含链表中所有元素的数组,顺序和链表中的顺序一致
	 * <p>
	 * <p>The returned array will be "safe" in that no references to it are
	 * maintained by this list.
	 * 返回的数组是安全的,数组中的元素对象将与链表中存储的对象无关
	 *
	 * (In other words, this method must allocate a new array).
	 * 换句话说,这个方法必须分配一个新的数组
	 *
	 * The caller is thus free to modify the returned array.
	 * 调用者可以随意修改数组及数组中的元素
	 * <p>
	 * <p>This method acts as bridge between array-based and collection-based
	 * APIs.
	 * 是数组与结合间的桥梁
	 *
	 * @return an array containing all of the elements in this list
	 * in proper sequence
	 * 返回一个包含链表中所有元素的数组,顺序和链表中的顺序一致
	 */
	public Object[] toArray() {
		//初始化数组 大小按链表大小初始化
		Object[] result = new Object[size];
		int i = 0;
		//遍历添加元素 x指向null时,为尾节点,循环停止
		for ( LinkedList.Node<E> x = first ; x != null ; x = x.next )
			result[i++] = x.item;
		return result;
	}

	/**
	 * Returns an array containing all of the elements in this list in
	 * proper sequence (from first to last element);
	 * 返回一个包含链表中元素的数组(顺序一致,从第一个到最后一个)
	 *
	 * the runtime type of the returned array is that of the specified array.
	 * 返回的数组是根据传入的数组决定的
	 *
	 * If the list fits in the specified array, it is returned therein.
	 * 如果list中的元素与数组合适,则生成后返回.
	 *
	 * Otherwise, a new array is allocated with the runtime type of the specified array and the size of this list.
	 * 否则,将在运行时,返回一个根据list的类型及数量生成的数组
	 *
	 * <p>
	 * <p>If the list fits in the specified array with room to spare
	 * (i.e.,the array has more elements than the list),
	 * the element in the array immediately following the end of the list is set to {@code null}.
	 * 如果list中的元素填充不满数组,则多余的数组位置将置null
	 *
	 * (This is useful in determining the length of the list <i>only</i> if
	 * the caller knows that the list does not contain any null elements.)
	 * <p>
	 * 这对于不包含null的list判断是否达到了最后一个元素至关重要.
	 *
	 * <p>Like the {@link #toArray()} method, this method acts as bridge between
	 * array-based and collection-based APIs.
	 * 像toArray()方法一样,本方法可以作为数组和集合接口的桥梁
	 *
	 * Further, this method allows precise control over the runtime type of the output array, and may,
	 * under certain circumstances, be used to save allocation costs.
	 * 此外, 此方法允许对输出数组的运行时类型进行精确控制, 并且在某些情况下可用于节省分配成本.
	 *
	 * <p>
	 * <p>Suppose {@code x} is a list known to contain only strings.
	 * The following code can be used to dump the list into a newly
	 * allocated array of {@code String}:
	 * 假设x是一个仅仅可以包含string的列表,下面的代码将分配一个string类型的数组
	 * <p>
	 * <pre>
	 *     //x.toArray(new String[0]) toArray()方法将在传入的数组new String[0]长度不够时返回一个根据x长度大小重新分配的新数组.
	 *     String[] y = x.toArray(new String[0]);</pre>
	 * <p>
	 *
	 * Note that {@code toArray(new Object[0])} is identical in function to
	 * {@code toArray()}.
	 * 注意:toArray(new Object[0])与toArray()方法相同.
	 *
	 * @param a the array into which the elements of the list are to
	 *          be stored, if it is big enough; otherwise, a new array of the
	 *          same runtime type is allocated for this purpose.
	 *          如果a的长度够,则list中的元素将会被按照顺序放入数组中,否则将会新分配一个数组后放入.
	 *
	 * @return an array containing the elements of the list 返回一个包含链表中元素的数组
	 *
	 * @throws ArrayStoreException  if the runtime type of the specified array
	 *                              is not a supertype of the runtime type of every element in
	 *                              this list
	 *                              如果数组的泛型与list中的泛型不一致时抛出ArrayStoreException异常
	 *
	 * @throws NullPointerException if the specified array is null 如果指定的数组为null时抛出空指针
	 */
	@SuppressWarnings("unchecked")
	public <T> T[] toArray( T[] a ) {
		if ( a.length < size )
			a = (T[]) java.lang.reflect.Array.newInstance(
					a.getClass().getComponentType(), size );
		int i = 0;
		Object[] result = a;
		for ( LinkedList.Node<E> x = first ; x != null ; x = x.next )
			result[i++] = x.item;
		//数组长度超过list后 将紧接着list的最后一个元素置为null
		if ( a.length > size )
			a[size] = null;

		return a;
	}

	private static final long serialVersionUID = 876323262645176354L;

	/**
	 * Saves the state of this {@code LinkedList} instance to a stream
	 * (that is, serializes it).
	 * 序列化方法
	 *
	 * @serialData The size of the list (the number of elements it
	 * contains) is emitted (int), followed by all of its
	 * elements (each an Object) in the proper order.
	 */
	private void writeObject( java.io.ObjectOutputStream s )
			throws java.io.IOException {
		// Write out any hidden serialization magic
		s.defaultWriteObject();

		// Write out size
		s.writeInt( size );

		// Write out all elements in the proper order.
		for ( LinkedList.Node<E> x = first ; x != null ; x = x.next )
			s.writeObject( x.item );
	}

	/**
	 * Reconstitutes this {@code LinkedList} instance from a stream
	 * (that is, deserializes it).
	 */
	@SuppressWarnings("unchecked")
	private void readObject( java.io.ObjectInputStream s )
			throws java.io.IOException, ClassNotFoundException {
		// Read in any hidden serialization magic
		s.defaultReadObject();

		// Read in size
		int size = s.readInt();

		// Read in all elements in the proper order.
		for ( int i = 0 ; i < size ; i++ )
			linkLast( (E) s.readObject() );
	}

	/**
	 * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
	 * and <em>fail-fast</em> {@link Spliterator} over the elements in this
	 * list.
	 * <p>
	 * <p>The {@code Spliterator} reports {@link Spliterator#SIZED} and
	 * {@link Spliterator#ORDERED}.  Overriding implementations should document
	 * the reporting of additional characteristic values.
	 *
	 * @return a {@code Spliterator} over the elements in this list
	 * @implNote The {@code Spliterator} additionally reports {@link Spliterator#SUBSIZED}
	 * and implements {@code trySplit} to permit limited parallelism..
	 * @since 1.8
	 */
	@Override
	public Spliterator<E> spliterator() {
		return new LinkedList.LLSpliterator<E>( this, -1, 0 );
	}

	/**
	 * A customized variant of Spliterators.IteratorSpliterator
	 */
	static final class LLSpliterator<E> implements Spliterator<E> {
		static final int BATCH_UNIT = 1 << 10;  // batch array size increment
		static final int MAX_BATCH = 1 << 25;  // max batch array size;
		final LinkedList<E> list; // null OK unless traversed
		LinkedList.Node<E> current;      // current node; null until initialized
		int est;              // size estimate; -1 until first needed
		int expectedModCount; // initialized when est set
		int batch;            // batch size for splits

		LLSpliterator( LinkedList<E> list, int est, int expectedModCount ) {
			this.list = list;
			this.est = est;
			this.expectedModCount = expectedModCount;
		}

		final int getEst() {
			int s; // force initialization
			final LinkedList<E> lst;
			if ( (s = est) < 0 ) {
				if ( (lst = list) == null )
					s = est = 0;
				else {
					expectedModCount = lst.modCount;
					current = lst.first;
					s = est = lst.size;
				}
			}
			return s;
		}

		public long estimateSize() {
			return (long) getEst();
		}

		public Spliterator<E> trySplit() {
			LinkedList.Node<E> p;
			int s = getEst();
			if ( s > 1 && (p = current) != null ) {
				int n = batch + BATCH_UNIT;
				if ( n > s )
					n = s;
				if ( n > MAX_BATCH )
					n = MAX_BATCH;
				Object[] a = new Object[n];
				int j = 0;
				do {
					a[j++] = p.item;
				} while ( (p = p.next) != null && j < n );
				current = p;
				batch = j;
				est = s - j;
				return Spliterators.spliterator( a, 0, j, Spliterator.ORDERED );
			}
			return null;
		}

		public void forEachRemaining( Consumer<? super E> action ) {
			LinkedList.Node<E> p;
			int n;
			if ( action == null ) throw new NullPointerException();
			if ( (n = getEst()) > 0 && (p = current) != null ) {
				current = null;
				est = 0;
				do {
					E e = p.item;
					p = p.next;
					action.accept( e );
				} while ( p != null && --n > 0 );
			}
			if ( list.modCount != expectedModCount )
				throw new ConcurrentModificationException();
		}

		public boolean tryAdvance( Consumer<? super E> action ) {
			LinkedList.Node<E> p;
			if ( action == null ) throw new NullPointerException();
			if ( getEst() > 0 && (p = current) != null ) {
				--est;
				E e = p.item;
				current = p.next;
				action.accept( e );
				if ( list.modCount != expectedModCount )
					throw new ConcurrentModificationException();
				return true;
			}
			return false;
		}

		public int characteristics() {
			return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
		}
	}

}
