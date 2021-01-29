package ru.alex.testwork.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "history")
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@EqualsAndHashCode.Include
	@NotNull
	private LocalDate tradeDate;

	@Column(name = "sec_id", insertable = false, updatable = false, nullable = false)
	private String secId;

	@NotNull
	private Double numTrades;

	@NotNull
	private Double open;

	@NotNull
	private Double close;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "sec_id", referencedColumnName = "sec_id")
	private Securities securities;

}
