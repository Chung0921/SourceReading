package com.chung.source.reading.collections.source.set;

/*
 * Copyright (c) 1997, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.*;

/**
 * A collection that contains no duplicate elements.
 * 一个包含不重复元素的集合.
 * <p>
 * More formally, sets contain no pair of elements <code>e1</code> and <code>e2</code> such that
 * <code>e1.equals(e2)</code>, and at most one null element.
 * 更严谨的说,set集合包含不重复的元素e1和e2,即调用e1.equals(e2)不可相等,且至少一个元素不为null元素.
 * <p>
 * As implied by its name, this interface models the mathematical <i>set</i> abstraction.
 * 正如它的名称所暗示的,这个接口建模了数学抽象.
 * <p>
 * <p>The <tt>Set</tt> interface places additional stipulations, beyond those inherited from the <tt>Collection</tt> interface, on the contracts of all
 * constructors and on the contracts of the <tt>add</tt>, <tt>equals</tt> and <tt>hashCode</tt> methods.
 * Set接口除了拥有Collection的规定接口外,还添加了其他接口如添加,是否相等,以及hashCode方法.
 * <p>
 * Declarations for other inherited methods are also included here for convenience.
 * 为了方便起见,这里还包括其他继承方法的声明.
 * <p>
 * (The specifications accompanying these declarations have been tailored to the <tt>Set</tt> interface,
 * but they do not contain any additional stipulations.)
 * 这些特定的规则已按照collection中的规则和set的特性编写,不包含任何额外的约定.
 * <p>
 * <p>
 * <p>The additional stipulation on constructors is, not surprisingly,
 * 对于构造器来说 这些不足为奇,
 * that all constructors must create a set that contains no duplicate elements
 * 所有的构造器都必须保证创建的set集合不包含任何重复的元素
 * (as defined above).
 * (上述定义).
 * <p>
 * <p>
 * <p>Note: Great care must be exercised if mutable objects are used as set elements.
 * 注意:如果将可变对象用作集合元素,必须谨慎行事.
 * <p>
 * The behavior of a set is not specified if the value of an object
 * is changed in a manner that affects <tt>equals</tt> comparisons while the
 * object is an element in the set.
 * 如果可变对象放入set后两个对象变为相等时,结果不可预期.
 * <p>
 * A special case of this prohibition is that it is not permissible for a set to contain itself as an element.
 * 有一种特殊情况是:集合不允许包含其自身作为一个元素.
 * <p>
 * <p>Some set implementations have restrictions on the elements that they may contain.
 * 某些集合实现对它们可能包含的元素有严格的限制.
 * <p>
 * For example, some implementations prohibit null elements,
 * and some have restrictions on the types of their elements.
 * 举个例子,有些集合设定为不可包含null元素,有些则设置为不可包含其自身.
 * <p>
 * Attempting to add an ineligible element throws an unchecked exception, typically
 * <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.
 * 尝试添加一个不合格的元素会抛出一个未检查的异常,通常为空指针异常或类型转化异常.
 * <p>
 * Attempting to query the presence of an ineligible element may throw an exception, or it may simply return false;
 * 试图查询不合格元素的存在可能引发异常,也可能返回false;
 * <p>
 * some implementations will exhibit the former behavior and some will exhibit the latter.
 * 一些实现将表现出以前的行为,有些将表现后者.
 * <p>
 * More generally, attempting an operation on an ineligible element whose completion would not result in
 * the insertion of an ineligible element into the set may throw an exception or it may succeed,
 * at the option of the implementation.
 * 更普遍的情况是,尝试对不合格的元素进行操作,其结果不会导致将不合格元素插入到该集合中,因此可能会抛出一个异常,或者在实现选项的情况下有可能执行成功.
 * <p>
 * Such exceptions are marked as "optional" in the specification for this interface.
 * 在接口的规范中,这样的异常被标记为“可选”.
 * <p>
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <E> the type of elements maintained by this set
 * @author Josh Bloch
 * @author Neal Gafter
 * @see Collection
 * @see List
 * @see SortedSet
 * @see HashSet
 * @see TreeSet
 * @see AbstractSet
 * @see Collections#singleton(java.lang.Object)
 * @see Collections#EMPTY_SET
 * @since 1.2
 */
public interface Set<E> extends Collection<E> {
	// Query Operations  查询操作

	/**
	 * Returns the number of elements in this set (its cardinality).
	 * 返回集合中的元素数量.
	 * <p>
	 * If this set contains more than <tt>Integer.MAX_VALUE</tt> elements, returns <tt>Integer.MAX_VALUE</tt>.
	 * 如果集合中包含元素个数比Integer.MAX_VALUE还要多,则返回Integer.MAX_VALUE作为当前set的元素个数.
	 *
	 * @return the number of elements in this set (its cardinality)
	 * 返回集合中的元素数量.
	 */
	int size();

	/**
	 * Returns <tt>true</tt> if this set contains no elements.
	 * 返回true如果set不包含任何元素.
	 *
	 * @return <tt>true</tt> if this set contains no elements
	 * 返回true如果set不包含任何元素.
	 */
	boolean isEmpty();

	/**
	 * Returns <tt>true</tt> if this set contains the specified element.
	 * 如果集合包含指定元素则返回true.
	 * <p>
	 * More formally, returns <tt>true</tt> if and only if this set
	 * contains an element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 * 更严格的说,当且仅当集合包含元素e时返回true.
	 *
	 * @param o element whose presence in this set is to be tested 需要被测试的元素o
	 * @return <tt>true</tt> if this set contains the specified element 如果包含该元素o则返回true
	 * @throws ClassCastException   if the type of the specified element
	 *                              is incompatible with this set
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 *                              如果该指定元素o的类型与集合中的元素泛型不符则抛出此异常(可选的)
	 * @throws NullPointerException if the specified element is null and this
	 *                              set does not permit null elements
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 *                              如果该指定元素o为null而该set不允许包含null元素则抛出此异常(可选的)
	 */
	boolean contains( Object o );

	/**
	 * Returns an iterator over the elements in this set.
	 * 返回该set集合的一个迭代器.
	 * <p>
	 * The elements are returned in no particular order (unless this set is an instance of some class that provides a guarantee).
	 * 元素没有特定的顺序返回(除非这个集合是某个类的实例,它提供了具体的保证).
	 *
	 * @return an iterator over the elements in this set 集合中元素上的迭代器
	 */
	Iterator<E> iterator();

	/**
	 * Returns an array containing all of the elements in this set.
	 * 将该集合转换为一个包含其下所有元素的数组并返回.
	 * <p>
	 * If this set makes any guarantees as to what order its elements
	 * are returned by its iterator, this method must return the
	 * elements in the same order.
	 * 如果set的迭代器实现了某种约定,那么该方法必须与其迭代器返回的顺序一致.
	 * <p>
	 * <p>The returned array will be "safe" in that no references to it are maintained by this set.
	 * 返回的顺序将是"安全的",因为该集合不维护它的引用.
	 * <p>
	 * (In other words, this method must allocate a new array even if this set is backed by an array).
	 * 换句话说,该方法必须重新分配一个数组即使该集合的底层实现依赖数组.
	 * <p>
	 * The caller is thus free to modify the returned array.
	 * 调用者因此可以自由修改返回的数组.
	 * <p>
	 * <p>This method acts as bridge between array-based and collection-based APIs.
	 * 本方法作为array和collection之间的桥梁.
	 *
	 * @return an array containing all the elements in this set 返回一个数组包含set中的所有元素.
	 */
	Object[] toArray();

	/**
	 * Returns an array containing all of the elements in this set;
	 * 将该集合转换为一个包含其下所有元素的数组并返回.
	 * <p>
	 * the runtime type of the returned array is that of the specified array.
	 * 返回一个特定类型的数组,数组类型由运行时指定.
	 * <p>
	 * If the set fits in the specified array, it is returned therein.
	 * 如果set的类型大小与该数组符合,那么将会填充后返回.
	 * Otherwise, a new array is allocated with the runtime type of the
	 * specified array and the size of this set.
	 * 否则,将会根据set的数据大小及类型创建一个新的数组填充后返回.
	 * <p>
	 * <p>If this set fits in the specified array with room to spare
	 * (i.e., the array has more elements than this set), the element in
	 * the array immediately following the end of the set is set to
	 * <tt>null</tt>.
	 * 如果传入的数组大小比set大(意味着数组可以容纳更多的元素),那么该数组中存放的set最后一个元素的下一位置将会放入null元素.
	 * <p>
	 * (This is useful in determining the length of this set <i>only</i> if the caller knows that this set does not contain any null elements.)
	 * (如果调用者知道set不包含任何null元素时,将可以应用null元素的位置来判断set的数据大小.)
	 * <p>
	 * <p>If this set makes any guarantees as to what order its elements
	 * are returned by its iterator, this method must return the elements in the same order.
	 * 如果set的迭代器实现了某种约定,那么该方法必须与其迭代器返回的顺序一致.
	 * <p>
	 * <p>Like the {@link #toArray()} method, this method acts as bridge between
	 * array-based and collection-based APIs.
	 * 与toArray一样,本方法作为array和collection之间的桥梁.
	 * <p>
	 * Further, this method allows precise control over the runtime type of the output array, and may,
	 * under certain circumstances, be used to save allocation costs.
	 * 此外,该方法允许对输出数组的运行时类型进行精确控制,并且可以,在某些情况下用于节省分配成本.
	 * <p>
	 * <p>Suppose <tt>x</tt> is a set known to contain only strings.
	 * 假设x是一个仅可以包含string的集合.
	 * <p>
	 * 以下代码将可以把set中的所有元素存放到一个新的分配的数组中.
	 * The following code can be used to dump the set into a newly allocated array of <tt>String</tt>:
	 * <p>
	 * <pre>
	 *     String[] y = x.toArray(new String[0]);</pre>
	 * <p>
	 * <p>
	 * Note that <tt>toArray(new Object[0])</tt> is identical in function to <tt>toArray()</tt>.
	 * 注意:toArray(new String[0])与toArray()实际上完全相同.
	 *
	 * @param a the array into which the elements of this set are to be
	 *          stored, if it is big enough; otherwise, a new array of the same
	 *          runtime type is allocated for this purpose.
	 *          参数中的数组如果足够大,则其将接受set中所有元素的存储;
	 *          否则将会在运行时动态分配一个新的数组填充set元素后返回.
	 * @return an array containing all the elements in this set 返回一个包含set中所有元素的数组
	 * @throws ArrayStoreException  if the runtime type of the specified array
	 *                              is not a supertype of the runtime type of every element in this
	 *                              set
	 * @throws NullPointerException if the specified array is null 如果指定的array为null则抛出空指针异常
	 */
	<T> T[] toArray( T[] a );


	// Modification Operations 修改操作

	/**
	 * Adds the specified element to this set if it is not already present (optional operation).
	 * 添加一个指定的元素到set中,当且仅当该元素在集合中不存在(可选操作).
	 * <p>
	 * More formally, adds the specified element
	 * <tt>e</tt> to this set if the set contains no element <tt>e2</tt>
	 * such that
	 * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
	 * 更严谨的说,判断是否存在的依据是调用equals方法.如果等于则不可以再存放到set中.
	 * <p>
	 * If this set already contains the element, the call leaves the set
	 * unchanged and returns <tt>false</tt>.
	 * 如果set中已经包含此元素,则不继续执行放入元素的操作并返回false.
	 * <p>
	 * In combination with the restriction on constructors, this ensures that sets never contain duplicate elements.
	 * 与构造器的限定一致,它也确保set不包含重复的元素.
	 * <p>
	 * <p>The stipulation above does not imply that sets must accept all elements;
	 * 上面的规定并不意味着集合必须接受所有元素；
	 * <p>
	 * sets may refuse to add any particular element, including
	 * <tt>null</tt>, and throw an exception, as described in the
	 * specification for {@link Collection#add Collection.add}.
	 * set集合的add方法可能像Collection中描述的一样,拒绝添加一些特殊的元素,比如说null元素拒绝时抛出异常.
	 * <p>
	 * Individual set implementations should clearly document any
	 * restrictions on the elements that they may contain.
	 * 一些特定的set实现类应该在doc中明确说明他们可以包含的元素.
	 *
	 * @param e element to be added to this set 需要被添加到set中的元素
	 * @return <tt>true</tt> if this set did not already contain the specified element
	 * 如果set没有准备好放入指定元素时返回true
	 * @throws UnsupportedOperationException if the <tt>add</tt> operation
	 *                                       is not supported by this set
	 *                                       如果add方法对于此set不支持则抛出此异常
	 * @throws ClassCastException            if the class of the specified element
	 *                                       prevents it from being added to this set
	 *                                       如果set拒绝了特定的元素的添加则抛出此异常
	 * @throws NullPointerException          if the specified element is null and this
	 *                                       set does not permit null elements
	 *                                       如果指定的元素为null而set不允许放入null元素则抛出此异常
	 * @throws IllegalArgumentException      if some property of the specified element
	 *                                       prevents it from being added to this set
	 *                                       如果一些特定的条件或属性阻止了特定元素添加到set中则抛出此异常
	 */
	boolean add( E e );


	/**
	 * Removes the specified element from this set if it is present (optional operation).
	 * 从set中删除指定的元素,当且仅当该元素在集合中存在时(可选操作).
	 * <p>
	 * More formally, removes an element <tt>e</tt>
	 * such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
	 * this set contains such an element.
	 * 更严谨的说,移除的元素e需要与set中的元素满足.equals方法时才将此元素移除.
	 * <p>
	 * Returns <tt>true</tt> if this set
	 * contained the element (or equivalently, if this set changed as a
	 * result of the call).
	 * 如果set包含此元素或者set因此而改变则返回true.
	 * <p>
	 * (This set will not contain the element once the call returns.)
	 * 当方法调用返回时set中就不包含该元素了.
	 *
	 * @param o object to be removed from this set, if present 需要从set中删除的元素,当且仅当其在set中存在.
	 * @return <tt>true</tt> if this set contained the specified element 如果set包含该特定的元素则返回true
	 * @throws ClassCastException            if the type of the specified element
	 *                                       is incompatible with this set
	 *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
	 *                                       如果被删除的元素与set的泛型不符合时则抛出此异常
	 * @throws NullPointerException          if the specified element is null and this
	 *                                       set does not permit null elements
	 *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
	 *                                       如果指定的元素为null而set不允许放入null元素则抛出此异常
	 * @throws UnsupportedOperationException if the <tt>remove</tt> operation
	 *                                       is not supported by this set
	 *                                       如果该set不支持此操作时抛出此异常
	 */
	boolean remove( Object o );


	// Bulk Operations 批量操作

	/**
	 * Returns <tt>true</tt> if this set contains all of the elements of the specified collection.
	 * 如果set包含特定集合c中的所有元素时,返回true
	 * <p>
	 * If the specified collection is also a set, this method returns <tt>true</tt> if it is a <i>subset</i> of this set.
	 * 如果特定集合也是个set,且待比较集合c是当前集合set的子集,则方法返回true
	 *
	 * @param c collection to be checked for containment in this set 需要被判定所有元素是否包含在本set中的集合c
	 * @return <tt>true</tt> if this set contains all of the elements of the specified collection
	 * 如果set包含特定集合c中的所有元素时,返回true
	 * @throws ClassCastException   if the types of one or more elements
	 *                              in the specified collection are incompatible with this
	 *                              set
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>)
	 *                              如果指定集合c中有一个或多个元素的类型与set的泛型发生冲突时抛出此异常(可选操作)
	 * @throws NullPointerException if the specified collection contains one
	 *                              or more null elements and this set does not permit null
	 *                              elements
	 *                              (<a href="Collection.html#optional-restrictions">optional</a>),
	 *                              or if the specified collection is null
	 *                              如果指定集合c中包含一个或多个null元素且set不允许存放null元素时抛出此异常.
	 *                              或者指定的集合c为null则抛出此异常
	 * @see #contains(Object)
	 */
	boolean containsAll( Collection<?> c );

	/**
	 * Adds all of the elements in the specified collection to this set if
	 * they're not already present (optional operation).
	 * 当集合c中元素在set中均不存在时,执行添加操作(可选操作.)
	 * <p>
	 * If the specified collection is also a set, the <tt>addAll</tt> operation effectively
	 * modifies this set so that its value is the <i>union</i> of the two sets.
	 * 如果指定集合也是一个set,那么效率最高的方式是取两个set的并集.
	 * <p>
	 * The behavior of this operation is undefined if the specified
	 * collection is modified while the operation is in progress.
	 * 如果该操作在进行中时,c集合发生了改变则结果未知
	 *
	 * @param c collection containing elements to be added to this set
	 *          集合c中的元素将会被添加到集合set中
	 * @return <tt>true</tt> if this set changed as a result of the call
	 * 如果set在调用后发生了改变则返回true
	 * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
	 *                                       is not supported by this set
	 *                                       如果该set不支持此操作时抛出此异常
	 * @throws ClassCastException            if the class of an element of the
	 *                                       specified collection prevents it from being added to this set
	 *                                       如果指定集合c中有一个或多个元素的类型与set的泛型发生冲突时抛出此异常(可选操作)
	 * @throws NullPointerException          if the specified collection contains one
	 *                                       or more null elements and this set does not permit null
	 *                                       elements, or if the specified collection is null
	 *                                       如果指定集合c中包含一个或多个null元素且set不允许存放null元素时抛出此异常.
	 *                                       或者指定的集合c为null则抛出此异常
	 * @throws IllegalArgumentException      if some property of an element of the
	 *                                       specified collection prevents it from being added to this set
	 *                                       如果集合中某些特定的元素拒绝了c的添加则抛出此异常
	 * @see #add(Object)
	 */
	boolean addAll( Collection<? extends E> c );

	/**
	 * Retains only the elements in this set that are contained in the
	 * specified collection (optional operation).
	 * 对于set集合仅保留c集合中元素(可选操作).
	 * <p>
	 * In other words, removes from this set all of its elements that are not contained in the specified collection.
	 * 换句话说,移除set中除了在集合c中的所有元素.
	 * <p>
	 * If the specified collection is also a set, this
	 * operation effectively modifies this set so that its value is the
	 * <i>intersection</i> of the two sets.
	 * 如果指定的集合c也是一个set集合,那么更高效的方法是取两个集合的交集.
	 *
	 * @param c collection containing elements to be retained in this set
	 *          set中需要被保持的元素集合
	 * @return <tt>true</tt> if this set changed as a result of the call
	 * 如果set集合在本次操作后发生了改变则返回true
	 * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
	 *                                       is not supported by this set
	 *                                       如果该set不支持此操作时抛出此异常
	 * @throws ClassCastException            if the class of an element of this set
	 *                                       is incompatible with the specified collection
	 *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
	 *                                       如果指定集合c中有一个或多个元素的类型与set的泛型发生冲突时抛出此异常(可选操作)
	 * @throws NullPointerException          if this set contains a null element and the
	 *                                       specified collection does not permit null elements
	 *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
	 *                                       or if the specified collection is null
	 *                                       如果指定集合c中包含一个或多个null元素且set不允许存放null元素时抛出此异常.
	 *                                       或者指定的集合c为null则抛出此异常
	 * @see #remove(Object)
	 */
	boolean retainAll( Collection<?> c );

	/**
	 * Removes from this set all of its elements that are contained in the specified collection (optional operation).
	 * 从set中移除集合c中的所有元素(可选操作).
	 * <p>
	 * If the specified collection is also a set, this operation effectively modifies this
	 * set so that its value is the <i>asymmetric set difference</i> of  the two sets.
	 * 如果指定的集合c同样也是一个set,那么更高效的操作是对于两个set取差集.
	 *
	 * @param c collection containing elements to be removed from this set 需要从set中移除元素的集合
	 * @return <tt>true</tt> if this set changed as a result of the call
	 * 如果set集合在方法调用后发生了改变则返回true
	 * @throws UnsupportedOperationException if the <tt>removeAll</tt> operation
	 *                                       is not supported by this set
	 *                                       如果该set不支持此操作时抛出此异常
	 * @throws ClassCastException            if the class of an element of this set
	 *                                       is incompatible with the specified collection
	 *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
	 *                                       如果指定集合c中有一个或多个元素的类型与set的泛型发生冲突时抛出此异常(可选操作)
	 * @throws NullPointerException          if this set contains a null element and the
	 *                                       specified collection does not permit null elements
	 *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
	 *                                       or if the specified collection is null
	 *                                       如果指定集合c中包含一个或多个null元素且set不允许存放null元素时抛出此异常.
	 *                                       或者指定的集合c为null则抛出此异常
	 * @see #remove(Object)
	 * @see #contains(Object)
	 */
	boolean removeAll( Collection<?> c );

	/**
	 * Removes all of the elements from this set (optional operation).
	 * The set will be empty after this call returns.
	 * 从set中移除所有的元素(可选操作).
	 * 当该方法返回后,set集合为空集.
	 *
	 * @throws UnsupportedOperationException if the <tt>clear</tt> method
	 *                                       is not supported by this set
	 *                                       如果该set不支持此操作时抛出此异常
	 */
	void clear();


	// Comparison and hashing 比较和哈希

	/**
	 * Compares the specified object with this set for equality.
	 * 将set集合与obj进行相等性的比较.
	 * <p>
	 * Returns <tt>true</tt> if the specified object is also a set, the two sets
	 * have the same size, and every member of the specified set is
	 * contained in this set (or equivalently, every member of this set is contained in the specified set).
	 * 如果指定的obj也是一个set集合,且两个set有着同样的大小,每一个元素也都相同,(或者说,每一个元素都在另一个set中包含 ),则认为两个set是相等的,返回true.
	 * <p>
	 * This definition ensures that the equals method works properly across different implementations of the set interface.
	 * 这样的定义确保了equals方法可以通过set接口的不同实现类完成相同的功能.
	 *
	 * @param o object to be compared for equality with this set          需要与本set进行比较的obj
	 * @return <tt>true</tt> if the specified object is equal to this set 如果指定的obj与该set相等返回true
	 */
	boolean equals( Object o );

	/**
	 * Returns the hash code value for this set.
	 * 返回该set的哈希值.
	 *
	 * The hash code of a set is defined to be the sum of the hash codes of the elements in the set,
	 * where the hash code of a <tt>null</tt> element is defined to be zero.
	 * 返回set中各元素的hashCode之和,null元素hashCode为0.
	 *
	 * This ensures that <tt>s1.equals(s2)</tt> implies that
	 * <tt>s1.hashCode()==s2.hashCode()</tt> for any two sets <tt>s1</tt>
	 * and <tt>s2</tt>, as required by the general contract of
	 * {@link Object#hashCode}.
	 * 这种定义确保了两个集合相等.
	 *
	 * @return the hash code value for this set 返回该集合的hashCode编码值
	 * @see Object#equals(Object)
	 * @see java.util.Set#equals(Object)
	 */
	int hashCode();

	/**
	 * Creates a {@code Spliterator} over the elements in this set.
	 * 创建一个分配器.
	 *
	 * <p>
	 * <p>The {@code Spliterator} reports {@link Spliterator#DISTINCT}.
	 * Implementations should document the reporting of additional
	 * characteristic values.
	 *
	 * @return a {@code Spliterator} over the elements in this set
	 * @implSpec The default implementation creates a
	 * <em><a href="Spliterator.html#binding">late-binding</a></em> spliterator
	 * from the set's {@code Iterator}.  The spliterator inherits the
	 * <em>fail-fast</em> properties of the set's iterator.
	 * <p>
	 * The created {@code Spliterator} additionally reports
	 * {@link Spliterator#SIZED}.
	 * @implNote The created {@code Spliterator} additionally reports
	 * {@link Spliterator#SUBSIZED}.
	 * @since 1.8
	 */
	@Override
	default Spliterator<E> spliterator() {
		return Spliterators.spliterator( this, Spliterator.DISTINCT );
	}
}

