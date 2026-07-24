package app.entity;

import app.entity.enums.FoundationType;
import app.entity.enums.RoofType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "buildings")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "length", nullable = false)
    private double length;

    @Column(name = "width", nullable = false)
    private double width;

    @Column(name = "floors", nullable = false)
    private int floors;

    @Column(name = "floor_height", nullable = false)
    private double floorHeight;

    @Column(name = "wall_thickness", nullable = false)
    private double wallThickness;

    @Column(name = "roof_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoofType roofType;

    @Column(name = "foundation_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoundationType foundationType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_id", unique = true)
    private Project project;
}
