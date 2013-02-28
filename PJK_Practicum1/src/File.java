import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * A class representing a file with a name, a length, 
 * a time of creation and of last edit and certain rights
 * 
 * @invar	The name must be valid: consists of lower case and upper case letters,
 * 			numbers, dots (.), hyphens (-) and underscores (_).
 * 			The name must also consist of at least 1 character.
 * 			| isValidName(name)
 * @invar 	The size must be a valid size for every single file.
 * 			| isValidSize(size)
 * 
 * @version 1.0
 * @author Pieterjan Hoedt & Kenneth Verstraete
 *
 */
public class File {
	
	/*******************************************************************
	 * Constructors													   *
	 *******************************************************************/
	
	/**
	 * Initialize this new file with given name, size 
	 * and whether users are allowed to overwrite the file.
	 * 
	 * @param	name
	 * 			The name of this new file.
	 * @param	size
	 * 			The size of this new file.
	 * @param	writeable
	 * 			Indicates whether users can overwrite the file or not.
	 * @pre		The given size must be a valid size
	 * 			| isValidSize(size)
	 * @post	The name will be a valid name for this file.
	 * 			| isValidName(this.name)
	 * @effect	The given name will be set as the name of this file.
	 * 			| setName(name)
	 * @post	The given size will be set as the size of this file.
	 * @effect 	The given permission whether to allow writing or not
	 * 			will be set as the writableness of this file.
	 * 			| setWritable(writable)
	 */
	public File(String name, int size, boolean writeable) {
		setName(name);
		setSize(size);
		setWriteable(writeable);
		creationTime = new Date();
		setModificationTime(creationTime);
	}
	
	/**
	 * Initialize this new file with given name.
	 * 
	 * @param	name
	 * 			The name of this new file.
	 * @post	The name will be a valid name for this file.
	 * 			| isValidName(this.name)
	 * @effect	The given name will be set as the name of this file.
	 * 			| setName(name)
	 * @effect	The given size will equal 0.
	 * 			| this.getName() == 0
	 * @effect 	This file will be writable.
	 * 			| getWritable() == true
	 */
	public File(String name){
		this(name, 0, true);
	}
	
	/*******************************************************************
	 * name															   *
	 *******************************************************************/
	
	/**
	 * Check whether the name of this file is a valid name.
	 * 
	 * @param 	name
	 * 			The name to be checked.
	 * @return	True if the given name only contains 
	 * 			small or capital letters, digits, points(.),
	 * 			hyphens(-) and underscores(_) 
	 * 			and is at least 1 character long.
	 * 			False otherwise.
	 */
	public static boolean isValidName(String name) {
		
		boolean b = false;
		if(name != null) {
			Pattern p = Pattern.compile("[a-zA-Z_0-9.-]+");
			Matcher m = p.matcher(name);
			if(m.matches()) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * Return the name of this file.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Set the name of this file.
	 * 
	 * @param	name
	 * 			The new name of this file.
	 * @post	If the given name is a valid name, 
	 * 			then the name is set to the given name.
	 * 			| if(isValidName(this.name))
	 * 			|	then new.getName() == name
	 * @post	If the given name isn't a valid one,
	 * 			the name of this file remain unchanged.
	 * 			| if(!isValidName(this.name))
	 * 			|	then new.getName() == getName()
	 */
	private void setName(String name) {
		if(isValidName(name)) {
			this.name = name;
		}
	}
	
	/**
	 * Variable registering the name of this file.
	 */
	private String name = "file";
	
	/*******************************************************************
	 * size															   *
	 *******************************************************************/
	
	/**
	 * Return the size of this file.
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Check whether the given size is a valid size.
	 * 
	 * @param 	size
	 * 			The size to be checked.
	 * @return	True if the given size is not negative and
	 * 			does not exceed the maximum allowed size.
	 * 			| result == (size >= 0) && (size <= getMaxSize())
	 */
	public static boolean isValidSize(int size) {
		return (size >=0 && size <= getMaxSize());
	}
	
	/**
	 * Set the size of this file.
	 * 
	 * @param 	size
	 * 			The new size of this file.
	 * @pre		The size must be a valid size.
	 * 			| isValidSize(size)
	 * @post	The given size will be the new size of this file.
	 * 			| new.getSize() == size
	 */
	private void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Enlarge the file with a given number of bytes.
	 * 
	 * @param 	numberOfBytes
	 * 			The number of bytes that will be added 
	 * 			to the size of the file.
	 * @pre		The number of bytes must be a positive number.
	 * 			| numberOfBytes >= 0
	 * @effect	The size of the file is increased with the given number of bytes
	 * 			| setSize(this.getSize() + numberOfBytes)	
	 */
	public void enlarge(int numberOfBytes) {
		setSize(getSize() + numberOfBytes);
	}
	
	/**
	 * Shorten the file with a given number of bytes.
	 * 
	 * @param 	numberOfBytes
	 * 			The number of bytes that will be removed 
	 * 			from the size of the file.
	 * @pre		The number of bytes must be a positive number.
	 * 			| numberOfBytes >= 0
	 * @effect	The size of the file is decreased with the given number of bytes
	 * 			| setSize(this.getSize() - numberOfBytes)
	 */
	public void shorten(int numberOfBytes) {
		setSize(getSize() - numberOfBytes);
	}
	
	/**
	 * Variable registering the size of this file in bytes.
	 */
	private int size = 0;
	
	/**
	 * Return the maximum allowed size for all files.
	 */
	public static int getMaxSize() {
		return MAX_SIZE;
	}
	
	/**
	 * Variable registering the maximum allowed size for all files.
	 */
	private static final int MAX_SIZE = Integer.MAX_VALUE;
	
	/*******************************************************************
	 * creationTime													   *
	 *******************************************************************/
	
	/**
	 * Return the date of creation of this file.
	 */
	public Date getCreationtime() {
		return this.creationTime;
	}
	
	/**
	 * Variable registering the time of creation of this file.
	 */
	private final Date creationTime;
	
	/*******************************************************************
	 * modificationtime												   *
	 *******************************************************************/
	
	/**
	 * Return the date of the last modification.
	 */
	public Date getModificationTime() {
		return this.modificationTime;
	}
	
	/**
	 * Set the time of the last modification of this file.
	 * 
	 * @param 	modificationTime
	 * 			The new time of the last modification.
	 * 
	 */
	private void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}
	
	/** 
	 * Variable registering the time of the last modification of this file.
	 */
	private Date modificationTime;
	
	/*******************************************************************
	 * writeable													   *
	 *******************************************************************/
	
	/**
	 * Return whether this file is writeable or not.
	 */
	public boolean isWriteable() {
		return this.isWriteable;
	}
	
	/**
	 * 
	 */
	private void setWriteable(boolean writeable) {
		this.isWriteable = writeable;
	}
	
	/**
	 * Variable registering whether this file can be overwritten or not. 
	 */
	private boolean isWriteable = true;
}
