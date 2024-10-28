/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author carli
 */
@Entity
@Table(name = "tblBlocks")
public class BlockEntity implements Serializable {

    @Id
    @Column(name = "idBlock", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motive", nullable = false, length = 200)
    private String motive;

    @Column(name = "blockDate", nullable = false)
    private LocalDate blockDate;

    @Column(name = "withdrawalDate", nullable = true)
    private LocalDate withdrawalDate;

    @ManyToOne
    @JoinColumn(name = "idStudent", nullable = false)
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "idRule", nullable = false)
    private RuleEntity rule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BlockEntity)) {
            return false;
        }
        BlockEntity other = (BlockEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.BlockEntity[ id=" + id + " ]";
    }

}
