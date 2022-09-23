/**
 * 
 */
package com.veeru.uploaddownloadservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Virupaksha K
 *
 */

@Entity
@Table(name = "Filedata")
public class FileEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(length = 100)
	private String id;
	private String name;
	private String type;
	@Lob
	private byte[] data;

	public FileEntity() {
	}

	public FileEntity(String id, String name, String type, byte[] data) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public byte[] getData() {
		return data;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
