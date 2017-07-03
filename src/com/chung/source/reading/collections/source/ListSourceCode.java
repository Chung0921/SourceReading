package com.chung.source.reading.collections.source;

import com.chung.source.reading.collections.test.ArrayListTest;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * Created by Chung.
 * Usage:
 * Description: 本类中均为源码
 * Create dateTime: 17/5/8
 */
public class ListSourceCode {

//package java.util;
//
//import java.util.function.UnaryOperator;

	/**
	 * An ordered collection (also known as a <i>sequence</i>).
	 * 一个有顺序的集合(像队列一样).
	 *
	 * The user of this interface has precise control over where in the list each element is inserted.
	 * 用户可以通过本接口,操作list集合中每一个被插入过的元素.
	 *
	 * The user can access elements by their integer index (position in the list), and search for elements in the list.<p>
	 * 用户可以通过元素的索引(在list集合中的位置)来访问它们,也可用通过索引在list中来查找该元素.
	 *
	 * Unlike sets, lists typically allow duplicate elements.
	 * 与Set集合不同,list集合通常允许有重复的元素.
	 *
	 * More formally, lists typically allow pairs of elements <tt>e1</tt> and <tt>e2</tt>
	 * such that <tt>e1.equals(e2)</tt>, and they typically allow multiple
	 * null elements if they allow null elements at all.
	 * 正式地,list集合通常允许比对元素.使用元素1.equals(元素2)进行比较,就算元素1与元素2都为空元素也可以.
	 *
	 * It is not inconceivable that someone might wish to implement a list that prohibits duplicates, by
	 * throwing runtime exceptions when the user attempts to insert them, but we expect this usage to be rare.<p>
	 * 对于有些人来说,有些人可能引用list接口在用户插入重复元素时抛出运行时异常,从而避免有重复元素,这是不可能实现的.但是我们希望这种用法尽可能少.
	 *
	 * The <tt>List</tt> interface places additional stipulations, beyond those
	 * specified in the <tt>Collection</tt> interface, on the contracts of the
	 * <tt>iterator</tt>, <tt>add</tt>, <tt>remove</tt>, <tt>equals</tt>, and
	 * <tt>hashCode</tt> methods.
	 * List接口中设置了一些额外的规定,除Collection接口的<tt>iterator</tt>, <tt>add</tt>, <tt>remove</tt>, <tt>equals</tt>, and
	 * <tt>hashCode</tt>方法以外.
	 *
	 * Declarations for other inherited methods are also included here for convenience.<p>
	 * 声明其他继承的方法同样也会方便的被添加进来
	 *
	 * The <tt>List</tt> interface provides four methods for positional (indexed) access to list elements.
	 * List接口提供了4种通过位置来访问list集合中元素的方法
	 *
	 * Lists (like Java arrays) are zero based.
	 * List集合同数组一样是从0开始的.
	 *
	 * Note that these operations may execute in time proportional to the index value for some implementations (the
	 * <tt>LinkedList</tt> class, for example).
	 * 注意,一些操作可能会在某些继承接口的类中有比例的及时的执行,如LinkedList.
	 * @see com.chung.source.reading.collections.source.list.LinkedList
	 *
	 * Thus, iterating over the elements in a list is typically preferable to indexing
	 * through it if the caller does not know the implementation.<p>
	 * 所以,通常通过List来进行迭代在集合中的元素,如果调用方不知道具体实现的话.
	 *
	 * The <tt>List</tt> interface provides a special iterator, called a <tt>ListIterator</tt>, that allows element
	 * insertion and replacement, and bidirectional access in addition to the normal operations that the
	 * <tt>Iterator</tt> interface provides.
	 * List接口提供了特殊的迭代器,名为ListIterator,它可以除了Iterator接口提供的方法外,还可以允许元素增加或替换及双向访问.
	 *
	 * A method is provided to obtain a list iterator that starts at a specified position in the list.<p>
	 * 它提供了一种方法以获取从列表中指定位置开始的列表迭代器.
	 * @see ListIterator
	 *
	 * The <tt>List</tt> interface provides two methods to search for a specified
	 * object.
	 * List接口提供了两种方法用于搜寻特定的对象.
	 *
	 * From a performance standpoint, these methods should be used with caution.
	 * In many implementations they will perform costly linear searches.<p>
	 * 从性能角度来看,应谨慎使用这些方法.在许多实现中,它们将执行昂贵的线性搜索. Ps.复杂度很高!!
	 *
	 * The <tt>List</tt> interface provides two methods to efficiently insert and
	 * remove multiple elements at an arbitrary point in the list.<p>
	 * List接口提供了两个在list列表的任意位置上增添或删除多个元素的高效方法.
	 *
	 * Note: While it is permissible for lists to contain themselves as elements,
	 * extreme caution is advised: the <tt>equals</tt> and <tt>hashCode</tt>
	 * methods are no longer well defined on such a list.
	 * 注意: 虽然list集合可以将他们本身最为元素,但需要非常谨慎.因为equals方法和hashCode方法已经不能再这样的list中很好的定义了.
	 *
	 * <p>Some list implementations have restrictions on the elements that
	 * they may contain.
	 * 某些list集合可能对他们包含的元素进行了限制.
	 *
	 * For example, some implementations prohibit null elements, and some have restrictions on the types of their elements.
	 * 举个例子,有些实现类将严格定义他们的元素类型从而会避免null元素.
	 *
	 * Attempting to add an ineligible element throws an unchecked exception, typically
	 * <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.
	 * 试图添加不合格的元素会引发未检查的异常,通常抛出NullPointerException或ClassCastException.
	 *
	 * Attempting to query the presence of an ineligible element may throw an exception,
	 * or it may simply return false; some implementations will exhibit the former
	 * behavior and some will exhibit the latter.
	 * 试图查询不合格元素的存在可能引发异常,或者将会返回false;一些实例将会展现之前的异常,而有些则会展示后者.
	 *
	 * More generally, attempting an operation on an ineligible element whose completion would not result in the insertion
	 * of an ineligible element into the list may throw an exception or it may succeed, at the option of the implementation.
	 * Such exceptions are marked as "optional" in the specification for this interface.
	 * 在该接口的规范中,此类异常标记为可选所以一般来说,尝试对不合格的元素进行操作,其插入列表将会不成功,同时会抛出异常.或者在某些实例中可能会成功.
	 *
	 * <p>This interface is a member of the
	 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
	 * 官方doc地址: http://docs.oracle.com/javase/8/docs/api/java/util/List.html
	 * Java Collections Framework</a>.
	 *
	 * @param <E> the type of elements in this list
	 *            E代表该类中元素泛型
	 *
	 * @author  Josh Bloch
	 * @author  Neal Gafter
	 * @see Collection
	 * @see Set
	 * @see com.chung.source.reading.collections.source.list.ArrayList
	 * @see com.chung.source.reading.collections.source.list.LinkedList
	 * @see Vector
	 * @see Arrays#asList(Object[])
	 * @see Collections#nCopies(int, Object)
	 * @see Collections#EMPTY_LIST
	 * @see AbstractList
	 * @see AbstractSequentialList
	 * @since 1.2
	 */

	public interface List<E> extends Collection<E> {
		// Query Operations 查询操作

		/**
		 * Returns the number of elements in this list.  If this list contains
		 * more than <tt>Integer.MAX_VALUE</tt> elements, returns
		 * <tt>Integer.MAX_VALUE</tt>.
		 * 返回list列表中的元素个数.如果列表个数超出了 整形最大值 ,那么将返回整形最大值作为该列表元素数量.
		 *
		 * @return the number of elements in this list 返回列表中元素的个数
		 */
		int size();

		/**
		 * Returns <tt>true</tt> if this list contains no elements.
		 *
		 * @return <tt>true</tt> if this list contains no elements
		 *
		 * 返回该集合是否为空
		 * 返回值为真,当且仅当该list列表不包含任何元素.
		 */
		boolean isEmpty();

		/**
		 * Returns <tt>true</tt> if this list contains the specified element.
		 * 判断list列表中是否包含待检测的元素.
		 * More formally, returns <tt>true</tt> if and only if this list contains
		 * at least one element <tt>e</tt> such that
		 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
		 * 更严谨的说,如果返回真则该list列表中至少含有一个指定待检测的元素.
		 * o为形参 该方法将会判断
		 *
		 * @param o element whose presence in this list is to be tested
		 *        o为list列表中元素是否存在的待检测元素
		 * @return <tt>true</tt> if this list contains the specified element
		 *          返回true 当list列表中包含此元素时
		 * @throws ClassCastException if the type of the specified element
		 *         is incompatible with this list
		 *         当o元素类型与list列表中的元素类型不一致时将抛出 类型转换异常 ClassCastException
		 * (<a href="Collection.html#optional-restrictions">optional</a>)
		 * @throws NullPointerException if the specified element is null and this
		 *         list does not permit null elements
		 *         当元素o为null且list列表不允许存在空元素时将抛出 空指针异常 NullPointerException
		 * (<a href="Collection.html#optional-restrictions">optional</a>)
		 */
		boolean contains(Object o);

		/**
		 * Returns an iterator over the elements in this list in proper sequence.
		 * 以正确的顺序返回此列表中元素的迭代器
		 * @return an iterator over the elements in this list in proper sequence
		 * 返回迭代器
		 */
		Iterator<E> iterator();

		/**
		 * Returns an array containing all of the elements in this list in proper
		 * sequence (from first to last element).
		 * 返回一个数组, 其中包含此列表中所有元素的正确顺序(从第一个到最后一个元素)
		 *
		 * <p>The returned array will be "safe" in that no references to it are
		 * maintained by this list.  (In other words, this method must
		 * allocate a new array even if this list is backed by an array).
		 * The caller is thus free to modify the returned array.
		 * 返回的数组将是 "安全" 的, 因为该列表没有对它的引用维护。(换而言之,该方法必须配一个新数组, 即使该列表是由数组支持)
		 * <p>This method acts as bridge between array-based and collection-based
		 * APIs.
		 * 此方法是collection集合与array数组相互转换的桥梁
		 *
		 * @return an array containing all of the elements in this list in proper
		 *         sequence
		 *         返回一个拥有list中全部元素且顺序相同的数组
		 * @see Arrays#asList(Object[]) 此方法将数组转换为list类型
		 */
		Object[] toArray();

		/**
		 * Returns an array containing all of the elements in this list in
		 * proper sequence (from first to last element);
		 * 返回一个数组, 其中包含此列表中所有元素的正确顺序(从第一个到最后一个元素)
		 * the runtime type of the returned array is that of the specified array.
		 * 返回数组的类型时调用此方法时指定的.
		 *
		 * If the list fits in the specified array, it is returned therein.
		 * 如果该列表与指定的数组条件合适,则将会返回一个数组, 其中包含此列表中所有元素的正确顺序.
		 *
		 * Otherwise, a new array is allocated with the runtime type of the specified array and the size of this list.
		 * 否则,将使用指定的类型和此列表的大小分配一个新的数组.
		 *
		 * <p>If the list fits in the specified array with room to spare (i.e.,
		 * the array has more elements than the list), the element in the array
		 * immediately following the end of the list is set to <tt>null</tt>.
		 * 如果列表适合指定的数组, 且具有备用空间 (即, 数组的元素多于列表),在数组中的紧跟在列表元素以后的元素均设置为null.
		 *
		 * (This is useful in determining the length of the list <i>only</i> if
		 * the caller knows that the list does not contain any null elements.)
		 * 当且仅当调用者知道列表中不包含null元素时,此时确定列表长度非常有用.
		 *
		 * <p>Like the {@link #toArray()} method, this method acts as bridge between
		 * array-based and collection-based APIs.  Further, this method allows
		 * precise control over the runtime type of the output array, and may,
		 * under certain circumstances, be used to save allocation costs.
		 * 与toArray()方法一样, 此方法是collection集合与array数组相互转换的桥梁.
		 * 进一步来说,这个方法允许精确的在运行时控制输出数组的类型,并且可以在某些情况下, 用于节约分配成本.
		 *
		 * <p>Suppose <tt>x</tt> is a list known to contain only strings.
		 * The following code can be used to dump the list into a newly
		 * allocated array of <tt>String</tt>:
		 * 假设x是一个list集合,在集合中仅包含string元素,下面的代码将会转储该list为一个分配的string类型数组
		 *
		 * <pre>{@code
		 *     String[] y = x.toArray(new String[0]);
		 * }</pre>
		 *
		 * Note that <tt>toArray(new Object[0])</tt> is identical in function to
		 * <tt>toArray()</tt>.
		 * 注意: toArray(new Object[0])与函数toArray()相同.
		 *
		 * !!!传入参数写入new Object[0] 将会在运行时根据list大小 动态决定 新分配的数组大小!!!
		 * @see ArrayListTest#testTransferToArray()
		 *
		 * @param a the array into which the elements of this list are to
		 *          be stored, if it is big enough; otherwise, a new array of the
		 *          same runtime type is allocated for this purpose.
		 *        a 要将此列表的元素存储到其中的数组 (如果足够大);否则, 将为此目的分配一个新的相同运行时类型的数组。
		 *        !!!传入参数写入new Object[0] 将会在运行时根据list大小 动态决定 新分配的数组大小!!!
		 *
		 * @return an array containing the elements of this list
		 *         返回一个包含该list列表所有元素的数组
		 * @throws ArrayStoreException if the runtime type of the specified array
		 *         is not a supertype of the runtime type of every element in
		 *         this list
		 *         如果运行时发现数组指定的类型与list中元素的类型不相同或不为list元素类型的父类则抛出 ArrayStoreException异常
		 *
		 * @throws NullPointerException if the specified array is null
		 *         如果指定的数组为 null 则抛出 NullPointerException异常
		 */
		<T> T[] toArray(T[] a);


		// Modification Operations 修改操作

		/**
		 * Appends the specified element to the end of this list (optional operation).
		 * 将指定的元素追加到此列表的末尾 (可选操作)。
		 *
		 * <p>Lists that support this operation may place limitations on what elements may be added to this list.
		 * 支持此操作的列表可能会对可以添加到此列表中的元素施加限制.
		 * In particular, some lists will refuse to add null elements, and others will impose
		 * restrictions on the type of elements that may be added.
		 * 特别情况,一些列表将拒绝添加 null 元素,而其他清单将对可能添加的元素的类型施加限制。
		 * List classes should clearly specify in their documentation any restrictions on what elements may be added.
		 * 列表类应在文档中明确指定对哪些元素可以添加的任何限制。
		 *
		 * @param e element to be appended to this list
		 *        e 要追加到此列表的元素
		 * @return <tt>true</tt> (as specified by {@link Collection#add})
		 *          返回true 如果此集合因调用而更改
		 * @throws UnsupportedOperationException if the <tt>add</tt> operation
		 *         is not supported by this list
		 *         如果add操作对于本list不支持 则抛出 UnsupportedOperationException 异常
		 * @throws ClassCastException if the class of the specified element
		 *         prevents it from being added to this list
		 *         如果指定元素的类阻止它添加到此列表中 则抛出 ClassCastException 异常
		 * @throws NullPointerException if the specified element is null and this
		 *         list does not permit null elements
		 *         如果此列表不允许插入空元素而该元素为null 则抛出 NullPointerException 异常
		 * @throws IllegalArgumentException if some property of this element
		 *         prevents it from being added to this list
		 *         如果此元素的某些属性阻止将其添加到此列表中 则抛出 ClassCastException 异常
		 */
		boolean add(E e);

		/**
		 * Removes the first occurrence of the specified element from this list, if it is present (optional operation).
		 * 从此列表中移除指定元素的第一个匹配项 (如果存在) (可选操作)。
		 *
		 * If this list does not contain the element, it is unchanged.
		 * 如果此列表不包含元素, 则它不变。
		 * More formally, removes the element with the lowest index <tt>i</tt> such that
		 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
		 * (if such an element exists).
		 * 更严谨的说, 删除具有最低索引为i的元素(多个相同元素则删除最低索引的一个!!!) 该索引对应的元素存在则被删除
		 * Returns <tt>true</tt> if this list contained the specified element (or equivalently, if this list changed
		 * as a result of the call).
		 * 返回真 如果此列表包含指定的元素,或等效地,如果此列表已更改.
		 *
		 * @param o element to be removed from this list, if present
		 *          要从此列表中删除的 o 元素 (如果存在)
		 *
		 * @return <tt>true</tt> if this list contained the specified element
		 *          如果此列表包含指定的元素 返回true
		 * @throws ClassCastException if the type of the specified element is incompatible with this list
		 *          如果指定元素的类型与此列表不兼容 抛出 ClassCastException 异常
		 * (<a href="Collection.html#optional-restrictions">optional</a>)
		 *
		 * @throws NullPointerException if the specified element is null and this list does not permit null elements
		 * (<a href="Collection.html#optional-restrictions">optional</a>)
		 *          如果指定的元素为 null, 并且此列表不允许空元素 抛出 NullPointerException 异常
		 *
		 * @throws UnsupportedOperationException if the <tt>remove</tt> operation is not supported by this list
		 *          如果此列表不支持remove操作 抛出 UnsupportedOperationException 异常
		 */
		boolean remove(Object o);


		// Bulk Modification Operations 批量修改操作

		/**
		 * Returns <tt>true</tt> if this list contains all of the elements of the
		 * specified collection.
		 * 如果list包含传入Collection对象的所有元素则返回true
		 *
		 * @param  c collection to be checked for containment in this list
		 *         c 待与list中元素进行检查的collection集合对象
		 *
		 * @return <tt>true</tt> if this list contains all of the elements of the
		 *         specified collection
		 *         如果list包含传入Collection对象的所有元素则返回true
		 *
		 * @throws ClassCastException if the types of one or more elements
		 *         in the specified collection are incompatible with this
		 *         list
		 *         如果传入的集合对象中有一个或多个与集合指定的泛型冲突的 抛出类型转换异常 ClassCastException
		 * (<a href="Collection.html#optional-restrictions">optional</a>)
		 *
		 * @throws NullPointerException if the specified collection contains one
		 *         or more null elements and this list does not permit null
		 *         elements
		 *         (<a href="Collection.html#optional-restrictions">optional</a>),
		 *         or if the specified collection is null
		 *
		 *         如果传入的集合对象中有一个或多个null对象且list集合不允许null对象 或者如果传入的colloection对象为null
		 *         则抛出空指针异常 NullPointerException
		 *
		 * @see #contains(Object)
		 */
		boolean containsAll(Collection<?> c);

		/**
		 * Appends all of the elements in the specified collection to the end of this list,
		 * 将指定集合中的所有元素追加到此列表的末尾
		 * in the order that they are returned by the specified collection's iterator (optional operation).
		 * 按指定集合的迭代器返回的顺序 (可选操作)
		 * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
		 * 如果在操作过程中修改指定的集合, 则此操作的行为未知
		 * (Note that this will occur if the specified collection is this list, and it's nonempty.)
		 * 请注意, 如果指定的集合是此列表, 并且它是非空的, 则会发生这种情况。
		 *
		 * @param c collection containing elements to be added to this list
		 *          包含要添加到此列表的元素的 c 集合
		 * @return <tt>true</tt> if this list changed as a result of the call
		 *          如果此列表因调用而更改 将返回true
		 *
		 * @throws UnsupportedOperationException if the <tt>addAll</tt> operation is not supported by this list
		 *          如果addAll操作对于本list不支持 则抛出 UnsupportedOperationException 异常
		 *
		 * @throws ClassCastException if the class of an element of the specified collection prevents it from being added to this list
		 *         如果指定元素的类阻止它添加到此列表中 则抛出 ClassCastException 异常
		 *
		 * @throws NullPointerException if the specified collection contains one
		 *         or more null elements and this list does not permit null
		 *         elements, or if the specified collection is null
		 *         如果此列表不允许插入空元素而该元素为null 则抛出 NullPointerException 异常
		 *
		 * @throws IllegalArgumentException if some property of an element of the
		 *         specified collection prevents it from being added to this list
		 *         如果此元素的某些属性阻止将其添加到此列表中 则抛出 ClassCastException 异常
		 *
		 * @see #add(Object)
		 */
		boolean addAll(Collection<? extends E> c);

		/**
		 * Inserts all of the elements in the specified collection into this list at the specified position (optional operation).
		 * 将指定集合中的所有元素插入到此列表中的指定位置 (可选操作)。
		 *
		 * Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices).
		 * 将当前位于该位置 (如果有) 的元素和右侧的任何后续元素 (增加它们的索引) 转移。(即:从index开始的元素向后移动)
		 *
		 * The new elements will appear in this list in the order that they are returned by the specified collection's iterator.
		 * 新元素将按指定集合的迭代器返回的顺序显示在该列表中。
		 *
		 * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
		 * (Note that this will occur if the specified collection is this list, and it's nonempty.)
		 * 如果在操作过程中修改指定的集合, 则此操作的结果未知。
		 * 请注意, 如果指定的集合是此列表, 并且它是非空的, 则会发生这种情况。
		 *
		 * @param index index at which to insert the first element from the specified collection
		 *              从指定集合中插入第一个元素的索引
		 *
		 * @param c collection containing elements to be added to this list
		 *         包含要添加到此列表的元素的 c 集合
		 *
		 * @return <tt>true</tt> if this list changed as a result of the call
		 *         如果此列表因调用而更改 将返回true
		 *
		 * @throws UnsupportedOperationException if the <tt>addAll</tt> operation is not supported by this list
		 *         如果addAll操作对于本list不支持 则抛出 UnsupportedOperationException 异常
		 *
		 * @throws ClassCastException if the class of an element of the specified collection prevents it from being added to this list
		 *         如果指定元素的类阻止它添加到此列表中 则抛出 ClassCastException 异常
		 *
		 * @throws NullPointerException if the specified collection contains one
		 *         or more null elements and this list does not permit null
		 *         elements, or if the specified collection is null
		 *         如果此列表不允许插入空元素而该元素为null 则抛出 NullPointerException 异常
		 *
		 * @throws IllegalArgumentException if some property of an element of the
		 *         specified collection prevents it from being added to this list
		 *         如果此元素的某些属性阻止将其添加到此列表中 则抛出 ClassCastException 异常
		 *
		 * @throws IndexOutOfBoundsException if the index is out of range
		 *         (<tt>index &lt; 0 || index &gt; size()</tt>)
		 *         当index<0或者index大于列表大小时 将会抛出越界异常 IndexOutOfBoundsException
		 */
		boolean addAll(int index, Collection<? extends E> c);

		/**
		 * Removes from this list all of its elements that are contained in the specified collection (optional operation).
		 * 从此列表中移除指定集合中包含的所有元素 (可选操作)。
		 *
		 * @param c collection containing elements to be removed from this list
		 *          包含要从此列表中移除的元素的集合
		 *
		 * @return <tt>true</tt> if this list changed as a result of the call
		 *         如果此列表因调用而更改 将返回true
		 *
		 * @throws UnsupportedOperationException if the <tt>removeAll</tt> operation is not supported by this list
		 *         如果removeAll操作对于本list不支持 则抛出 UnsupportedOperationException 异常
		 *
		 * @throws ClassCastException if the class of an element of this list is incompatible with the specified collection
		 * (<a href="Collection.html#optional-restrictions">optional</a>)
		 *         如果指定元素的类与列表中的类型不符 则抛出 ClassCastException 异常
		 *
		 * @throws NullPointerException if this list contains a null element and the
		 *         specified collection does not permit null elements
		 *         (<a href="Collection.html#optional-restrictions">optional</a>),
		 *         or if the specified collection is null
		 *         如果此列表包含null元素而指定的collection不允许包含null元素 或者指定的collection为null 则抛出 NullPointerException 异常
		 * @see #remove(Object)
		 * @see #contains(Object)
		 */
		boolean removeAll(Collection<?> c);

		/**
		 * Retains only the elements in this list that are contained in the specified collection (optional operation).
		 * 仅保留此列表中包含在指定集合中的元素 (可选操作)。
		 * In other words, removes from this list all of its elements that are not contained in the specified collection.
		 * 换句话说, 从该列表中移除指定集合中未包含的所有元素。
		 *
		 * @param c collection containing elements to be retained in this list
		 *          包含要保留在此列表中的元素的集合c
		 *
		 * @return <tt>true</tt> if this list changed as a result of the call
		 *          如果此列表因调用而更改 将返回true
		 *
		 * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation is not supported by this list
		 *         如果此list集合不支持retainAll方法则抛出 UnsupportedOperationException 异常
		 *
		 * @throws ClassCastException if the class of an element of this list is incompatible with the specified collection
		 * (<a href="Collection.html#optional-restrictions">optional</a>)
		 *          如果list中元素类型与collection中元素类型不符 则抛出类型转换异常 ClassCastException
		 *
		 * @throws NullPointerException if this list contains a null element and the
		 *         specified collection does not permit null elements
		 *         (<a href="Collection.html#optional-restrictions">optional</a>),
		 *         or if the specified collection is null
		 *         如果list包含null元素且collection不允许包含null元素,或者如果指定的collection为null 则将抛出空指针异常 NullPointerException
		 *
		 * @see #remove(Object)
		 * @see #contains(Object)
		 */
		boolean retainAll(Collection<?> c);

		/**
		 * Replaces each element of this list with the result of applying the operator to that element.
		 * 将此列表中的每个元素替换为该元素应用运算符的结果。
		 * Errors or runtime exceptions thrown by the operator are relayed to the caller.
		 * 运算符引发的错误或运行时异常将中继到调用方。
		 *
		 * @implSpec
		 * The default implementation is equivalent to, for this {@code list}:
		 * <pre>{@code
		 *     final ListIterator<E> li = list.listIterator();
		 *     while (li.hasNext()) {
		 *         li.set(operator.apply(li.next()));
		 *     }
		 * }</pre>
		 *
		 * If the list's list-iterator does not support the {@code set} operation then an {@code UnsupportedOperationException}
		 * will be thrown when replacing the first element.
		 * 如果列表的列表-迭代器不支持 {@code set} 操作, 则在替换第一个元素时将引发一个 {@code UnsupportedOperationException}。
		 *
		 * @param operator the operator to apply to each element
		 *          要应用于每个元素的运算符
		 *
		 * @throws UnsupportedOperationException if this list is unmodifiable.
		 *         Implementations may throw this exception if an element
		 *         cannot be replaced or if, in general, modification is not
		 *         supported
		 *         假设list是不可变的.如果无法替换某个元素,该接口的实现类则可能会抛出此异常
		 *
		 * @throws NullPointerException if the specified operator is null or
		 *         if the operator result is a null value and this list does
		 *         not permit null elements
		 *         (<a href="Collection.html#optional-restrictions">optional</a>)
		 *         当list中不允许有空元素时,如果指定的operator是空的或者operator的结果是一个null类型将会抛出空指针异常 NullPointerException
		 * @since 1.8
		 */
		default void replaceAll(UnaryOperator<E> operator) {
			Objects.requireNonNull(operator);
			final ListIterator<E> li = this.listIterator();
			while (li.hasNext()) {
				li.set(operator.apply(li.next()));
			}
		}

		/**
		 * Sorts this list according to the order induced by the specified
		 * {@link Comparator}.
		 * 根据指定的顺序对此列表进行排序 需要编写Comparator
		 *
		 * <p>All elements in this list must be <i>mutually comparable</i> using the specified comparator
		 * (that is, {@code c.compare(e1, e2)} must not throw a {@code ClassCastException} for any elements {@code e1} and {@code e2} in the list).
		 * 此列表中的所有元素都必须使用指定的比较器 <i> 相互比较 </i>
		 * (也就是说, {@code c.compare(e1, e2)} 对于list中的任意一个元素 e1 e2来说肯定不会抛出ClassCastException异常。
		 *
		 * <p>If the specified comparator is {@code null} then all elements in this
		 * list must implement the {@link Comparable} interface and the elements'
		 * {@linkplain Comparable natural ordering} should be used.
		 * 如果指定的比较器为null, 则这个list集合中集成了Comparable接口每个元素将会按插入的顺序进行排序
		 *
		 * <p>This list must be modifiable, but need not be resizable.
		 * 此列表必须是可修改的, 但不需要调整大小。
		 *
		 * @implSpec
		 * The default implementation obtains an array containing all elements in this list, sorts the array,
		 *
		 * 缺省的实现获取包含此列表中所有元素的数组, 对数组进行排序,
		 *
		 * and iterates over this list resetting each element from the corresponding position in the array.
		 * 并迭代循环访问此列表,从数组中相应的位置重置每个元素
		 *
		 * (This avoids the n<sup>2</sup> log(n) performance that would result from attempting to sort a linked list in place.)
		 * 这将避免尝试对链接列表进行排序, 从而避免提高复杂度 n的2次方 [log(n)]
		 *
		 * @implNote
		 * This implementation is a stable, adaptive, iterative merge sort that requires far fewer than n lg(n) comparisons
		 * when the input array is partially sorted,
		 * while offering the performance of a traditional mergesort when the input array is randomly ordered.
		 * 此实现是一个稳定的、自适应的、迭代的归并排序, 当输入的数组部分被排序后传入,它的复杂度远比 nlg(n)小,提高了传统归并排序的性能
		 *
		 * If the input array is nearly sorted, the implementation requires approximately n comparisons.
		 * 如果输入数组几乎被排序, 则实现需要近似地进行 n 比较。
		 * Temporary storage requirements vary from a small constant for nearly sorted input arrays to n/2 object references for randomly ordered input arrays.
		 * 临时存储要求因几乎排序的输入数组的小常量而异, 用于随机有序输入数组的 n/2 对象引用。
		 *
		 * <p>The implementation takes equal advantage of ascending and descending order in its input array,
		 * and can take advantage of ascending and descending order in different parts of the same input array.
		 * 该实现在其输入阵列中同样具有升序和降序的优势, 并且可以利用同一输入数组的不同部分的升序和降序顺序
		 * It is well-suited to merging two or more sorted arrays:
		 * 它非常适合合并两个或多个排序的数组
		 * simply concatenate the arrays and sort the resulting array.
		 * 只需串联数组并对结果数组进行排序。
		 *
		 * <p>The implementation was adapted from Tim Peters's list sort for Python
		 * (<a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
		 * TimSort</a>).  It uses techniques from Peter McIlroy's "Optimistic
		 * Sorting and Information Theoretic Complexity", in Proceedings of the
		 * Fourth Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474,
		 * January 1993.
		 *
		 * @param c the {@code Comparator} used to compare list elements.
		 *          A {@code null} value indicates that the elements'
		 *          {@linkplain Comparable natural ordering} should be used
		 *          比较器用作比较list集合中的元素,一个null值将会使用自然序进行排序.
		 *
		 * @throws ClassCastException if the list contains elements that are not
		 *         <i>mutually comparable</i> using the specified comparator
		 *          如果list中的元素不适合使用传入的比较器进行相互比较则抛出此异常 ClassCastException
		 *
		 * @throws UnsupportedOperationException if the list's list-iterator does not support the {@code set} operation
		 *          如果列表的列表-迭代器不支持 {@code set} 操作 则抛出 UnsupportedOperationException 异常
		 *
		 * @throws IllegalArgumentException
		 *         (<a href="Collection.html#optional-restrictions">optional</a>)
		 *         if the comparator is found to violate the {@link Comparator} contract
		 *         如果发现比较器违反了 {@link Comparator} 规则
		 *
		 * @since 1.8
		 */
		@SuppressWarnings({"unchecked", "rawtypes"})
		default void sort(Comparator<? super E> c) {
			Object[] a = this.toArray();
			Arrays.sort(a, (Comparator) c);
			ListIterator<E> i = this.listIterator();
			for (Object e : a) {
				i.next();
				i.set((E) e);
			}
		}

		/**
		 * Removes all of the elements from this list (optional operation).
		 * 删除此列表中的所有元素 (可选操作)。
		 * The list will be empty after this call returns.
		 * 当此调用返回后,list列表将为空
		 *
		 * @throws UnsupportedOperationException if the <tt>clear</tt> operation is not supported by this list
		 *                                       如果此列表不支持clear操作,则抛出UnsupportedOperationException异常
		 */
		void clear();


		// Comparison and hashing 比较和哈希

		/**
		 * Compares the specified object with this list for equality.
		 * 将指定对象与此列表进行比较以实现相等。
		 *
		 * Returns <tt>true</tt> if and only if the specified object is also a list,
		 * 如果并且仅当指定的对象也是列表时, 返回 <tt> true </tt>
		 *
		 * both lists have the same size, and all corresponding pairs of elements in the two lists are <i>equal</i>.
		 * 两个列表都具有相同的大小, 并且这两个列表中的所有对应元素对都是 <i> 相等 </i>
		 *
		 * (Two elements <tt>e1</tt> and <tt>e2</tt> are <i>equal</i> if <tt>(e1==null ? e2==null : e1.equals(e2))</tt>.)
		 * (两个元素 <tt> e1==null </tt> 和 <tt> equals </tt> 是 <i> 相等 </i> if <tt> (e2==null: 等于 ()) </tt>。
		 *
		 * In other words, two lists are defined to be equal if they contain the same elements in the same order.
		 * 换句话说, 如果两个列表以相同的顺序包含相同的元素, 则定义为相等。
		 *
		 * This definition ensures that the equals method works properly across different implementations of the <tt>List</tt> interface.
		 * 此定义确保等号方法在 <tt> list </tt> 接口的不同实现中正常工作。
		 *
		 * @param o the object to be compared for equality with this list
		 *          o 要与此列表进行相等比较的对象
		 * @return <tt>true</tt> if the specified object is equal to this list
		 *          如果指定的对象等于此列表, 则为 <tt> true </tt>
		 */
		boolean equals(Object o);

		/**
		 * Returns the hash code value for this list.
		 * 返回此列表的哈希代码值。
		 * The hash code of a list is defined to be the result of the following calculation:
		 * 列表的哈希代码被定义为以下计算结果:
		 *
		 * <pre>{@code
		 *     int hashCode = 1;
		 *     for (E e : list)
		 *         hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
		 * }</pre>
		 *
		 * This ensures that <tt>list1.equals(list2)</tt> implies that
		 * <tt>list1.hashCode()==list2.hashCode()</tt> for any two lists,
		 * <tt>list1</tt> and <tt>list2</tt>, as required by the general
		 * contract of {@link Object#hashCode}.
		 *
		 * @return the hash code value for this list
		 * @see Object#equals(Object)
		 * @see #equals(Object)
		 */
		int hashCode();


		// Positional Access Operations 位置访问操作

		/**
		 * Returns the element at the specified position in this list.
		 * 返回列表中指定位置的元素
		 *
		 * @param index index of the element to return
		 *              需要返回元素的位置
		 * @return the element at the specified position in this list
		 *         返回列表中指定位置的元素
		 * @throws IndexOutOfBoundsException if the index is out of range
		 *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
		 *         如果index<0 或 index > 列表的长度 则抛出异常 IndexOutOfBoundsException
		 */
		E get(int index);

		/**
		 * Replaces the element at the specified position in this list with the
		 * specified element (optional operation).
		 * 替换列表中指定位置的元素为传入的元素(可选操作)
		 *
		 * @param index index of the element to replace
		 *              指定需要替换的位置
		 * @param element element to be stored at the specified position
		 *                替换后将要被存储在list列表中的元素
		 * @return the element previously at the specified position
		 *          先前位于指定位置的元素
		 * @throws UnsupportedOperationException if the <tt>set</tt> operation
		 *         is not supported by this list
		 *         如果该list列表不支持此操作 则抛出 UnsupportedOperationException 异常
		 *
		 * @throws ClassCastException if the class of the specified element
		 *         prevents it from being added to this list
		 *         如果list列表不支持插入此类型的元素 则抛出 ClassCastException 异常
		 *
		 * @throws NullPointerException if the specified element is null and
		 *         this list does not permit null elements
		 *         如果list不允许null元素 而将要插入的元素为null元素 则抛出 NullPointerException 异常
		 *
		 * @throws IllegalArgumentException if some property of the specified
		 *         element prevents it from being added to this list
		 *         如果指定元素的某些属性禁止将其添加到此列表中
		 *
		 * @throws IndexOutOfBoundsException if the index is out of range
		 *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
		 *         如果index<0 或 index > 列表的长度 则抛出异常 IndexOutOfBoundsException
		 *
		 */
		E set(int index, E element);

		/**
		 * Inserts the specified element at the specified position in this list (optional operation).
		 * 将指定的元素插入此列表中的指定位置 (可选操作)。
		 * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
		 * 如果将要插入的元素位置 (如果有) 元素则该元素和它右边的任何后续元素将右移 (添加到它们的索引中)。
		 *
		 * @param index index at which the specified element is to be inserted
		 *              被添加的元素添加的位置
		 * @param element element to be inserted
		 *                被添加的元素
		 *
		 * @throws UnsupportedOperationException if the <tt>add</tt> operation
		 *         is not supported by this list
		 *         如果该list列表不支持此操作 则抛出 UnsupportedOperationException 异常
		 *
		 * @throws ClassCastException if the class of the specified element
		 *         prevents it from being added to this list
		 *         如果list列表不支持插入此类型的元素 则抛出 ClassCastException 异常
		 *
		 * @throws NullPointerException if the specified element is null and
		 *         this list does not permit null elements
		 *         如果list不允许null元素 而将要插入的元素为null元素 则抛出 NullPointerException 异常
		 *
		 * @throws IllegalArgumentException if some property of the specified
		 *         element prevents it from being added to this list
		 *         如果指定元素的某些属性禁止将其添加到此列表中
		 *
		 * @throws IndexOutOfBoundsException if the index is out of range
		 *         (<tt>index &lt; 0 || index &gt; size()</tt>)
		 *         如果index<0 或 index > 列表的长度 则抛出异常 IndexOutOfBoundsException
		 */
		void add(int index, E element);

		/**
		 * Removes the element at the specified position in this list (optional operation).
		 * 移除此列表中指定位置的元素 (可选操作)。
		 *
		 * Shifts any subsequent elements to the left (subtracts one from their indices).
		 * 将任何后续元素向左移动 (从索引中减去一个)。
		 *
		 * Returns the element that was removed from the list.
		 * 返回从列表中移除的元素。
		 *
		 * @param index the index of the element to be removed
		 *              被移除元素的位置
		 *
		 * @return the element previously at the specified position
		 *          先前在这个位置的元素 即被删除的元素
		 *
		 * @throws UnsupportedOperationException if the <tt>remove</tt> operation
		 *         is not supported by this list
		 *         如果该list列表不支持此操作 则抛出 UnsupportedOperationException 异常
		 *
		 * @throws IndexOutOfBoundsException if the index is out of range
		 *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
		 *         如果index<0 或 index > 列表的长度 则抛出异常 IndexOutOfBoundsException
		 */
		E remove(int index);


		// Search Operations 搜索操作

		/**
		 * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
		 * 返回此列表中指定元素的第一个匹配项的索引, 如果此列表不包含该元素, 则为-1。
		 *
		 * More formally, returns the lowest index <tt>i</tt> such that
		 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
		 * or -1 if there is no such index.
		 * 更确切的说,将返回index索引最低位置,例如:当且仅当元素存在一个或多个时返回最低的index否则如果元素不存在则返回-1
		 *
		 * @param o element to search for
		 *          需要被搜寻的元素
		 *
		 * @return the index of the first occurrence of the specified element in
		 *         this list, or -1 if this list does not contain the element
		 *         返回元素第一次出现在list中的index,否则返回-1 代表该列表不包含此元素
		 *
		 * @throws ClassCastException if the type of the specified element
		 *         is incompatible with this list
		 *         (<a href="Collection.html#optional-restrictions">optional</a>)
		 *         如果该list列表不支持此操作 则抛出 UnsupportedOperationException 异常
		 *
		 * @throws NullPointerException if the specified element is null and this
		 *         list does not permit null elements
		 *         (<a href="Collection.html#optional-restrictions">optional</a>)
		 *         如果list不允许null元素 而将要查询的元素为null元素 则抛出 NullPointerException 异常
		 */
		int indexOf(Object o);

		/**
		 * Returns the index of the last occurrence of the specified element
		 * in this list, or -1 if this list does not contain the element.
		 * More formally, returns the highest index <tt>i</tt> such that
		 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
		 * or -1 if there is no such index.
		 *返回此列表中指定元素的最后一个匹配项的索引, 如果此列表不包含该元素, 则为-1。
		 *
		 * @param o element to search for
		 *          需要被搜寻的元素
		 *
		 * @return the index of the last occurrence of the specified element in
		 *         this list, or -1 if this list does not contain the element
		 *         返回元素最后一次出现在list中的index,否则返回-1 代表该列表不包含此元素
		 *
		 * @throws ClassCastException if the type of the specified element
		 *         is incompatible with this list
		 *         (<a href="Collection.html#optional-restrictions">optional</a>)
		 *         如果该list列表不支持此操作 则抛出 UnsupportedOperationException 异常
		 *
		 * @throws NullPointerException if the specified element is null and this
		 *         list does not permit null elements
		 *         (<a href="Collection.html#optional-restrictions">optional</a>)
		 *         如果list不允许null元素 而将要查询的元素为null元素 则抛出 NullPointerException 异常
		 */
		int lastIndexOf(Object o);


		// List Iterators 列表迭代器

		/**
		 * Returns a list iterator over the elements in this list (in proper sequence).
		 * 返回此列表中元素的列表迭代器 (按正确顺序)。
		 * @return a list iterator over the elements in this list (in proper sequence)
		 */
		ListIterator<E> listIterator();

		/**
		 * Returns a list iterator over the elements in this list (in proper
		 * sequence), starting at the specified position in the list.
		 * 返回此列表中按开始index之后元素的列表迭代器 (按正确顺序)。
		 *
		 * The specified index indicates the first element that would be
		 * returned by an initial call to {@link ListIterator#next next}.
		 * 返回指定的index意味着第一个元素将会在迭代器调用.next()方法时获取到.
		 * An initial call to {@link ListIterator#previous previous} would
		 * return the element with the specified index minus one.
		 * 调用.previous()方法将会获取到指定index对应元素的前一个元素.
		 *
		 * @param index index of the first element to be returned from the list iterator (by a call to {@link ListIterator#next next})
		 *              index为第一个元素将会返回的索引
		 * @return a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list
		 *          列表中的元素的迭代器 (按照正确的顺序), 从列表中的指定位置开始
		 * @throws IndexOutOfBoundsException if the index is out of range
		 *         ({@code index < 0 || index > size()})
		 *         如果index<0 或 index > 列表的长度 则抛出异常 IndexOutOfBoundsException
		 */
		ListIterator<E> listIterator(int index);

		// View 查看

		/**
		 * Returns a view of the portion of this list between the specified <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.
		 * 返回此列表在指定的 [fromIndex ,toIndex )之间的list列表.
		 *
		 * (If <tt>fromIndex</tt> and <tt>toIndex</tt> are equal, the returned list is empty.)
		 * 如果fromIndex与toIndex相同,则返回的空list列表.
		 *
		 * The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa.
		 * 返回的列表基于的原先列表, 因此返回列表中的非结构的更改将反映在原有列表中,原有列表中的更改也会反映在子列表中 。
		 * The returned list supports all of the optional list operations supported by this list.<p>
		 * 返回的子列表拥有其原有列表所有可选列表操作。
		 *
		 * This method eliminates the need for explicit range operations (of he sort that commonly exist for arrays).
		 * 此方法消除了显式范围操作 (对于数组通常存在的排序) 的需要。
		 * Any operation that expects a list can be used as a range operation by passing a subList view instead of a whole list.
		 * 任何希望列表的操作都可以通过传递子列表视图而不是整个列表来用作范围操作。
		 * For example, the following idiom removes a range of elements from a list:
		 * 例如, 下面的代码,从列表中移除一系列元素
		 * <pre>{@code
		 *      list.subList(from, to).clear();
		 * }</pre>
		 * Similar idioms may be constructed for <tt>indexOf</tt> and
		 * <tt>lastIndexOf</tt>, and all of the algorithms in the
		 * <tt>Collections</tt> class can be applied to a subList.<p>
		 *
		 * The semantics of the list returned by this method become undefined if
		 * the backing list (i.e., this list) is <i>structurally modified</i> in
		 * any way other than via the returned list.
		 * 如果原有列表 (即此列表) 以任何方式 修改了结构 除 (通过返回的列表) 以外, 则此方法返回的列表的引用将变为未定义。
		 *
		 * (Structural modifications are those that change the size of this list, or otherwise perturb it in
		 * such a fashion that iterations in progress may yield incorrect results.)
		 * 结构修改是那些更改此列表的大小, 或者以这种方式打扰它的版本, 正在进行的迭代可能产生不正确的结果。
		 *
		 * @param fromIndex low endpoint (inclusive) of the subList 起始index 包含
		 * @param toIndex high endpoint (exclusive) of the subList 终止index 不包含
		 * @return a view of the specified range within this list 返回在此范围内list列表的引用
		 * @throws IndexOutOfBoundsException for an illegal endpoint index value
		 *         (<tt>fromIndex &lt; 0 || toIndex &gt; size ||
		 *         fromIndex &gt; toIndex</tt>)
		 *
		 *         如果fromIndex<0 或 toIndex > 列表的长度 或 fromIndex > toIndex 则抛出异常 IndexOutOfBoundsException
		 */
		java.util.List<E> subList( int fromIndex, int toIndex);

		/**
		 * Creates a {@link Spliterator} over the elements in this list.
		 *
		 * <p>The {@code Spliterator} reports {@link Spliterator#SIZED} and
		 * {@link Spliterator#ORDERED}.  Implementations should document the
		 * reporting of additional characteristic values.
		 *
		 * @implSpec
		 * The default implementation creates a
		 * <em><a href="Spliterator.html#binding">late-binding</a></em> spliterator
		 * from the list's {@code Iterator}.  The spliterator inherits the
		 * <em>fail-fast</em> properties of the list's iterator.
		 *
		 * @implNote
		 * The created {@code Spliterator} additionally reports
		 * {@link Spliterator#SUBSIZED}.
		 *
		 * @return a {@code Spliterator} over the elements in this list
		 * @since 1.8
		 */
		@Override
		default Spliterator<E> spliterator() {
			return Spliterators.spliterator(this, Spliterator.ORDERED);
		}
	}

}
