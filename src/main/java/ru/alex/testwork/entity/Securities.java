package ru.alex.testwork.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "securities")
public class Securities implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@NaturalId
	@Column(name = "sec_id", nullable = false, unique = true)
	@Size(min = 4, message = "sec_id must be more than 3")
	private String secId;

	@NotNull
	private String regNumber;

	@NotNull
	@NotBlank(message = "name not blank")
	private String name;

	@NotNull
	@NotBlank(message = "emitent title not blank")
	private String emitentTitle;

	@ToString.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "securities", fetch = FetchType.LAZY)
	private Set<History> historySet;

}


