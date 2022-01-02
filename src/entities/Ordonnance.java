/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.HashMap;

import dto.ConsultationDTo;
import dto.MedicamentDTO;

/**
 *
 * @author junio
 */
public class Ordonnance {
    
  private int idOrdonnance;
  private ConsultationDTo consultation;
  
  private HashMap<Medicament, String> medicaments = new HashMap<Medicament, String>();
  private ArrayList<MedicamentDTO> medicamentsDto = new ArrayList<MedicamentDTO>();
    
    
    

    public Ordonnance() {
       
    }

    public Ordonnance(int idOrdonnance, ConsultationDTo consultation,HashMap<Medicament, String> medicaments) {
        this.idOrdonnance = idOrdonnance;
        this.consultation = consultation;
        this.medicaments=medicaments;
    }
    public Ordonnance(int idOrdonnance, ConsultationDTo consultation) {
        this.idOrdonnance = idOrdonnance;
        this.consultation = consultation;
       
    }
    public Ordonnance(ConsultationDTo consultation,HashMap<Medicament, String> medicaments) {
        
        this.consultation = consultation;
        this.medicaments=medicaments;
    }
    
    public Ordonnance(ConsultationDTo consultation) {
        
        this.consultation = consultation;
        
    }

    
    
    
    public int getIdOrdonnance() {
        return idOrdonnance;
    }

    public void setIdOrdonnance(int idOrdonnance) {
        this.idOrdonnance = idOrdonnance;
    }

    public ConsultationDTo getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationDTo consultation) {
        this.consultation = consultation;
    }

    public HashMap<Medicament, String> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(HashMap<Medicament, String> medicaments) {
        this.medicaments = medicaments;
    }
    public ArrayList<MedicamentDTO> getMedicamentsDto() {
        return medicamentsDto;
    }
    public void setMedicamentsDto() {
        this.medicamentsDto.clear();
         for (Medicament m : medicaments.keySet()) {
            MedicamentDTO medicamentDto = new MedicamentDTO();
            medicamentDto.setMedicament(m);
            medicamentDto.setPosologie(medicaments.get(m));       
            this.medicamentsDto.add(medicamentDto);
          }
        
    }
    
    
   



    
    
    
}
