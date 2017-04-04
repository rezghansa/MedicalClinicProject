/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

/**
 *
 * @author hansa
 */
public class Examinations {
    
    private     int patientId;
    private     String examinationDate;
    private     int examinationID;
    private	String general_height;
    private	String general_weight;
    private	String general_Pallor;
    private	String general_Jaundice;
    private	String general_Thin;          					
    private	String general_Obese;        					
    private	String general_oedemaankle;					 
    private	String general_periorbital;  					
    private	String general_dehydration; 				
    private	String general_sob;                				
    private	String cvs_pulseBpm;
    private	String cvs_regular;      					
    private	String cvs_irregular;  				
    private	String cvs_Bp; 
    private	String cvs_Heartdualrhythm;
    private	String cvs_triplerhythm;
    private	String cvs_murmurs;                   	
    private	String cvs_systolic;  					
    private	String cvs_diastolic;               	
    private	String rs_equalairentry;        		
    private	String rs_reducedairentry;   		
    private	String rs_vbs;                      		
    private	String rs_bbs;                     		
    private	String rs_creps;                  		
    private	String rs_rhonchi;                  		
    private	String ear_EarNormal;                 			
    private	String ear_Normalhearing;         			
    private	String ear_Wax;                    			
    private	String ear_OE;                     			
    private	String ear_traumaticperforation;  			
    private	String ear_AOM;                    			
    private	String ear_OME;                    			
    private	String ear_CSOM;                   			
    private	String ear_CP;                     			
    private	String ear_bleedingEar;             			
    private	String ear_Eardischarge;        			
    private	String ear_Granulationtissue;   			
    private	String ear_EarPolyp;                			
    private	String nose_Patent;                 			
    private	String nose_blocked;                			
    private	String nose_Senseofsmell;         			
    private	String nose_Fb;                     			
    private	String nose_DeviatedSeptum;        			
    private	String nose_BleedingS;               			
    private	String nose_Secretions;             			
    private	String nose_Polyp;                  			
    private	String nose_PND;                    			
    private	String thoat_Normal;                 			
    private	String thoat_Pharyngitis;                		
    private	String thoat_Tonsillitis;         				
    private	String thoat_NormalVC;                     		
    private	String thoat_Other;        						
    private	String amodoment_Distension;                 		
    private	String amodoment_Tenderness;                			
    private	String amodoment_Liver;
    private	String amodoment_Spleen;
    private	String amodoment_Kidney;
    private	String amodoment_AbodometOther;
    private	String amodoment_siteepigastricC;
    private	String amodoment_hypochondriacC;
    private	String amodoment_umbilicalC;
    private	String amodoment_lumbarL;
    private	String amodoment_lumbarR;		
    private	String amodoment_hypogastricL;
    private	String amodoment_hypogastricR;
    private	String amodoment_inguinalL;
    private	String amodoment_inguinalR;		
    private	String male_Swelling;         
    private	String male_MaleTenderness;           
    private	String male_Hydrocele;         
    private	String male_Balanitis;              
    private	String male_Urethraldischarge;   
    private	String male_fungalrash;         
    private	String male_MaleOther;
    private	String female_BreastsL; 
    private	String female_BreastsTL;
    private	String female_BreastsR;
    private	String female_BreastsTR; 
    private	String female_NipplesL;
    private	String female_NipplesTL;		
    private	String female_NipplesR;
    private	String female_NipplesTR;
    private	String female_Uterus;
    private	String female_LMP1;
    private	String female_LMP2;
    private	String female_LMP3;
    private	String female_Perineum;
    private	String female_FemaleOther;
    private	String cns_Normalcranialnerves;  
    private	String cns_Normalcranialnervestxt;
    private	String cns_Gaitnormal; 
    private	String cns_Gaitnormaltxt;
    private	String cns_speechnormal; 
    private	String cns_speechnormaltxt;
    private	String cns_Coordinationnormal;
    private	String cns_Coordinationnormaltxt; 
    private	String cns_Powernormal;
    private	String cns_Powernormaltxt;
    private	String cns_Sensorynormal;
    private	String cns_Sensorynormaltxt;
    private	String muskoskel_cervicalspinetenderness;          
    private	String muskoskel_thoracicspinetenderness;          
    private	String muskoskel_lumbosacralspinetenderness;      
    private	String muskoskel_SIJtenderness;                     
    private	String muskoskel_Swollenlargejoints;               
    private	String muskoskel_Tenderlargejoints;                
    private	String muskoskel_Swollensmalljoints;               
    private	String muskoskel_Tendersmalljoints;                
    private	String muskoskel_Chestwallribtenderness;          
    private	String muskoskel_Upperlimbtenderness;            
    private	String muskoskel_Lowerlimbtenderness;

    public int getExaminationID() {
        return examinationID;
    }

    public void setExaminationID(int examinationID) {
        this.examinationID = examinationID;
    }
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(String examinationDate) {
        this.examinationDate = examinationDate;
    }

    
    
    public String getGeneral_height() {
        return general_height;
    }

    public void setGeneral_height(String general_height) {
        this.general_height = general_height;
    }

    public String getGeneral_weight() {
        return general_weight;
    }

    public void setGeneral_weight(String general_weight) {
        this.general_weight = general_weight;
    }

    public String getGeneral_Pallor() {
        return general_Pallor;
    }

    public void setGeneral_Pallor(String general_Pallor) {
        this.general_Pallor = general_Pallor;
    }

    public String getGeneral_Jaundice() {
        return general_Jaundice;
    }

    public void setGeneral_Jaundice(String general_Jaundice) {
        this.general_Jaundice = general_Jaundice;
    }

    public String getGeneral_Thin() {
        return general_Thin;
    }

    public void setGeneral_Thin(String general_Thin) {
        this.general_Thin = general_Thin;
    }

    public String getGeneral_Obese() {
        return general_Obese;
    }

    public void setGeneral_Obese(String general_Obese) {
        this.general_Obese = general_Obese;
    }

    public String getGeneral_oedemaankle() {
        return general_oedemaankle;
    }

    public void setGeneral_oedemaankle(String general_oedemaankle) {
        this.general_oedemaankle = general_oedemaankle;
    }

    public String getGeneral_periorbital() {
        return general_periorbital;
    }

    public void setGeneral_periorbital(String general_periorbital) {
        this.general_periorbital = general_periorbital;
    }

    public String getGeneral_dehydration() {
        return general_dehydration;
    }

    public void setGeneral_dehydration(String general_dehydration) {
        this.general_dehydration = general_dehydration;
    }

    public String getGeneral_sob() {
        return general_sob;
    }

    public void setGeneral_sob(String general_sob) {
        this.general_sob = general_sob;
    }

    public String getCvs_pulseBpm() {
        return cvs_pulseBpm;
    }

    public void setCvs_pulseBpm(String cvs_pulseBpm) {
        this.cvs_pulseBpm = cvs_pulseBpm;
    }

    public String getCvs_regular() {
        return cvs_regular;
    }

    public void setCvs_regular(String cvs_regular) {
        this.cvs_regular = cvs_regular;
    }

    public String getCvs_irregular() {
        return cvs_irregular;
    }

    public void setCvs_irregular(String cvs_irregular) {
        this.cvs_irregular = cvs_irregular;
    }

    public String getCvs_Bp() {
        return cvs_Bp;
    }

    public void setCvs_Bp(String cvs_Bp) {
        this.cvs_Bp = cvs_Bp;
    }

    public String getCvs_Heartdualrhythm() {
        return cvs_Heartdualrhythm;
    }

    public void setCvs_Heartdualrhythm(String cvs_Heartdualrhythm) {
        this.cvs_Heartdualrhythm = cvs_Heartdualrhythm;
    }

    public String getCvs_triplerhythm() {
        return cvs_triplerhythm;
    }

    public void setCvs_triplerhythm(String cvs_triplerhythm) {
        this.cvs_triplerhythm = cvs_triplerhythm;
    }

    public String getCvs_murmurs() {
        return cvs_murmurs;
    }

    public void setCvs_murmurs(String cvs_murmurs) {
        this.cvs_murmurs = cvs_murmurs;
    }

    public String getCvs_systolic() {
        return cvs_systolic;
    }

    public void setCvs_systolic(String cvs_systolic) {
        this.cvs_systolic = cvs_systolic;
    }

    public String getCvs_diastolic() {
        return cvs_diastolic;
    }

    public void setCvs_diastolic(String cvs_diastolic) {
        this.cvs_diastolic = cvs_diastolic;
    }

    public String getRs_equalairentry() {
        return rs_equalairentry;
    }

    public void setRs_equalairentry(String rs_equalairentry) {
        this.rs_equalairentry = rs_equalairentry;
    }

    public String getRs_reducedairentry() {
        return rs_reducedairentry;
    }

    public void setRs_reducedairentry(String rs_reducedairentry) {
        this.rs_reducedairentry = rs_reducedairentry;
    }

    public String getRs_vbs() {
        return rs_vbs;
    }

    public void setRs_vbs(String rs_vbs) {
        this.rs_vbs = rs_vbs;
    }

    public String getRs_bbs() {
        return rs_bbs;
    }

    public void setRs_bbs(String rs_bbs) {
        this.rs_bbs = rs_bbs;
    }

    public String getRs_creps() {
        return rs_creps;
    }

    public void setRs_creps(String rs_creps) {
        this.rs_creps = rs_creps;
    }

    public String getRs_rhonchi() {
        return rs_rhonchi;
    }

    public void setRs_rhonchi(String rs_rhonchi) {
        this.rs_rhonchi = rs_rhonchi;
    }

    public String getEar_EarNormal() {
        return ear_EarNormal;
    }

    public void setEar_EarNormal(String ear_EarNormal) {
        this.ear_EarNormal = ear_EarNormal;
    }

    public String getEar_Normalhearing() {
        return ear_Normalhearing;
    }

    public void setEar_Normalhearing(String ear_Normalhearing) {
        this.ear_Normalhearing = ear_Normalhearing;
    }

    public String getEar_Wax() {
        return ear_Wax;
    }

    public void setEar_Wax(String ear_Wax) {
        this.ear_Wax = ear_Wax;
    }

    public String getEar_OE() {
        return ear_OE;
    }

    public void setEar_OE(String ear_OE) {
        this.ear_OE = ear_OE;
    }

    public String getEar_traumaticperforation() {
        return ear_traumaticperforation;
    }

    public void setEar_traumaticperforation(String ear_traumaticperforation) {
        this.ear_traumaticperforation = ear_traumaticperforation;
    }

    public String getEar_AOM() {
        return ear_AOM;
    }

    public void setEar_AOM(String ear_AOM) {
        this.ear_AOM = ear_AOM;
    }

    public String getEar_OME() {
        return ear_OME;
    }

    public void setEar_OME(String ear_OME) {
        this.ear_OME = ear_OME;
    }

    public String getEar_CSOM() {
        return ear_CSOM;
    }

    public void setEar_CSOM(String ear_CSOM) {
        this.ear_CSOM = ear_CSOM;
    }

    public String getEar_CP() {
        return ear_CP;
    }

    public void setEar_CP(String ear_CP) {
        this.ear_CP = ear_CP;
    }

    public String getEar_bleedingEar() {
        return ear_bleedingEar;
    }

    public void setEar_bleedingEar(String ear_bleedingEar) {
        this.ear_bleedingEar = ear_bleedingEar;
    }

    public String getEar_Eardischarge() {
        return ear_Eardischarge;
    }

    public void setEar_Eardischarge(String ear_Eardischarge) {
        this.ear_Eardischarge = ear_Eardischarge;
    }

    public String getEar_Granulationtissue() {
        return ear_Granulationtissue;
    }

    public void setEar_Granulationtissue(String ear_Granulationtissue) {
        this.ear_Granulationtissue = ear_Granulationtissue;
    }

    public String getEar_EarPolyp() {
        return ear_EarPolyp;
    }

    public void setEar_EarPolyp(String ear_EarPolyp) {
        this.ear_EarPolyp = ear_EarPolyp;
    }

    public String getNose_Patent() {
        return nose_Patent;
    }

    public void setNose_Patent(String nose_Patent) {
        this.nose_Patent = nose_Patent;
    }

    public String getNose_blocked() {
        return nose_blocked;
    }

    public void setNose_blocked(String nose_blocked) {
        this.nose_blocked = nose_blocked;
    }

    public String getNose_Senseofsmell() {
        return nose_Senseofsmell;
    }

    public void setNose_Senseofsmell(String nose_Senseofsmell) {
        this.nose_Senseofsmell = nose_Senseofsmell;
    }

    public String getNose_Fb() {
        return nose_Fb;
    }

    public void setNose_Fb(String nose_Fb) {
        this.nose_Fb = nose_Fb;
    }

    public String getNose_DeviatedSeptum() {
        return nose_DeviatedSeptum;
    }

    public void setNose_DeviatedSeptum(String nose_DeviatedSeptum) {
        this.nose_DeviatedSeptum = nose_DeviatedSeptum;
    }

    public String getNose_BleedingS() {
        return nose_BleedingS;
    }

    public void setNose_BleedingS(String nose_BleedingS) {
        this.nose_BleedingS = nose_BleedingS;
    }

    public String getNose_Secretions() {
        return nose_Secretions;
    }

    public void setNose_Secretions(String nose_Secretions) {
        this.nose_Secretions = nose_Secretions;
    }

    public String getNose_Polyp() {
        return nose_Polyp;
    }

    public void setNose_Polyp(String nose_Polyp) {
        this.nose_Polyp = nose_Polyp;
    }

    public String getNose_PND() {
        return nose_PND;
    }

    public void setNose_PND(String nose_PND) {
        this.nose_PND = nose_PND;
    }

    public String getThoat_Normal() {
        return thoat_Normal;
    }

    public void setThoat_Normal(String thoat_Normal) {
        this.thoat_Normal = thoat_Normal;
    }

    public String getThoat_Pharyngitis() {
        return thoat_Pharyngitis;
    }

    public void setThoat_Pharyngitis(String thoat_Pharyngitis) {
        this.thoat_Pharyngitis = thoat_Pharyngitis;
    }

    public String getThoat_Tonsillitis() {
        return thoat_Tonsillitis;
    }

    public void setThoat_Tonsillitis(String thoat_Tonsillitis) {
        this.thoat_Tonsillitis = thoat_Tonsillitis;
    }

    public String getThoat_NormalVC() {
        return thoat_NormalVC;
    }

    public void setThoat_NormalVC(String thoat_NormalVC) {
        this.thoat_NormalVC = thoat_NormalVC;
    }

    public String getThoat_Other() {
        return thoat_Other;
    }

    public void setThoat_Other(String thoat_Other) {
        this.thoat_Other = thoat_Other;
    }

    public String getAmodoment_Distension() {
        return amodoment_Distension;
    }

    public void setAmodoment_Distension(String amodoment_Distension) {
        this.amodoment_Distension = amodoment_Distension;
    }

    public String getAmodoment_Tenderness() {
        return amodoment_Tenderness;
    }

    public void setAmodoment_Tenderness(String amodoment_Tenderness) {
        this.amodoment_Tenderness = amodoment_Tenderness;
    }

    public String getAmodoment_Liver() {
        return amodoment_Liver;
    }

    public void setAmodoment_Liver(String amodoment_Liver) {
        this.amodoment_Liver = amodoment_Liver;
    }

    public String getAmodoment_Spleen() {
        return amodoment_Spleen;
    }

    public void setAmodoment_Spleen(String amodoment_Spleen) {
        this.amodoment_Spleen = amodoment_Spleen;
    }

    public String getAmodoment_Kidney() {
        return amodoment_Kidney;
    }

    public void setAmodoment_Kidney(String amodoment_Kidney) {
        this.amodoment_Kidney = amodoment_Kidney;
    }

    public String getAmodoment_AbodometOther() {
        return amodoment_AbodometOther;
    }

    public void setAmodoment_AbodometOther(String amodoment_AbodometOther) {
        this.amodoment_AbodometOther = amodoment_AbodometOther;
    }

    public String getAmodoment_siteepigastricC() {
        return amodoment_siteepigastricC;
    }

    public void setAmodoment_siteepigastricC(String amodoment_siteepigastricC) {
        this.amodoment_siteepigastricC = amodoment_siteepigastricC;
    }

    public String getAmodoment_hypochondriacC() {
        return amodoment_hypochondriacC;
    }

    public void setAmodoment_hypochondriacC(String amodoment_hypochondriacC) {
        this.amodoment_hypochondriacC = amodoment_hypochondriacC;
    }

    public String getAmodoment_umbilicalC() {
        return amodoment_umbilicalC;
    }

    public void setAmodoment_umbilicalC(String amodoment_umbilicalC) {
        this.amodoment_umbilicalC = amodoment_umbilicalC;
    }

    

    public String getAmodoment_lumbarL() {
        return amodoment_lumbarL;
    }

    public void setAmodoment_lumbarL(String amodoment_lumbarL) {
        this.amodoment_lumbarL = amodoment_lumbarL;
    }

    public String getAmodoment_lumbarR() {
        return amodoment_lumbarR;
    }

    public void setAmodoment_lumbarR(String amodoment_lumbarR) {
        this.amodoment_lumbarR = amodoment_lumbarR;
    }

    public String getAmodoment_hypogastricL() {
        return amodoment_hypogastricL;
    }

    public void setAmodoment_hypogastricL(String amodoment_hypogastricL) {
        this.amodoment_hypogastricL = amodoment_hypogastricL;
    }

    public String getAmodoment_hypogastricR() {
        return amodoment_hypogastricR;
    }

    public void setAmodoment_hypogastricR(String amodoment_hypogastricR) {
        this.amodoment_hypogastricR = amodoment_hypogastricR;
    }

    public String getAmodoment_inguinalL() {
        return amodoment_inguinalL;
    }

    public void setAmodoment_inguinalL(String amodoment_inguinalL) {
        this.amodoment_inguinalL = amodoment_inguinalL;
    }

    public String getAmodoment_inguinalR() {
        return amodoment_inguinalR;
    }

    public void setAmodoment_inguinalR(String amodoment_inguinalR) {
        this.amodoment_inguinalR = amodoment_inguinalR;
    }

    public String getMale_Swelling() {
        return male_Swelling;
    }

    public void setMale_Swelling(String male_Swelling) {
        this.male_Swelling = male_Swelling;
    }

    public String getMale_MaleTenderness() {
        return male_MaleTenderness;
    }

    public void setMale_MaleTenderness(String male_MaleTenderness) {
        this.male_MaleTenderness = male_MaleTenderness;
    }

    public String getMale_Hydrocele() {
        return male_Hydrocele;
    }

    public void setMale_Hydrocele(String male_Hydrocele) {
        this.male_Hydrocele = male_Hydrocele;
    }

    public String getMale_Balanitis() {
        return male_Balanitis;
    }

    public void setMale_Balanitis(String male_Balanitis) {
        this.male_Balanitis = male_Balanitis;
    }

    public String getMale_Urethraldischarge() {
        return male_Urethraldischarge;
    }

    public void setMale_Urethraldischarge(String male_Urethraldischarge) {
        this.male_Urethraldischarge = male_Urethraldischarge;
    }

    public String getMale_fungalrash() {
        return male_fungalrash;
    }

    public void setMale_fungalrash(String male_fungalrash) {
        this.male_fungalrash = male_fungalrash;
    }

    public String getMale_MaleOther() {
        return male_MaleOther;
    }

    public void setMale_MaleOther(String male_MaleOther) {
        this.male_MaleOther = male_MaleOther;
    }

    public String getFemale_BreastsL() {
        return female_BreastsL;
    }

    public void setFemale_BreastsL(String female_BreastsL) {
        this.female_BreastsL = female_BreastsL;
    }

    public String getFemale_BreastsTL() {
        return female_BreastsTL;
    }

    public void setFemale_BreastsTL(String female_BreastsTL) {
        this.female_BreastsTL = female_BreastsTL;
    }

    public String getFemale_BreastsR() {
        return female_BreastsR;
    }

    public void setFemale_BreastsR(String female_BreastsR) {
        this.female_BreastsR = female_BreastsR;
    }

    public String getFemale_BreastsTR() {
        return female_BreastsTR;
    }

    public void setFemale_BreastsTR(String female_BreastsTR) {
        this.female_BreastsTR = female_BreastsTR;
    }

    public String getFemale_NipplesL() {
        return female_NipplesL;
    }

    public void setFemale_NipplesL(String female_NipplesL) {
        this.female_NipplesL = female_NipplesL;
    }

    public String getFemale_NipplesTL() {
        return female_NipplesTL;
    }

    public void setFemale_NipplesTL(String female_NipplesTL) {
        this.female_NipplesTL = female_NipplesTL;
    }

    public String getFemale_NipplesR() {
        return female_NipplesR;
    }

    public void setFemale_NipplesR(String female_NipplesR) {
        this.female_NipplesR = female_NipplesR;
    }

    public String getFemale_NipplesTR() {
        return female_NipplesTR;
    }

    public void setFemale_NipplesTR(String female_NipplesTR) {
        this.female_NipplesTR = female_NipplesTR;
    }

    public String getFemale_Uterus() {
        return female_Uterus;
    }

    public void setFemale_Uterus(String female_Uterus) {
        this.female_Uterus = female_Uterus;
    }

    public String getFemale_LMP1() {
        return female_LMP1;
    }

    public void setFemale_LMP1(String female_LMP1) {
        this.female_LMP1 = female_LMP1;
    }

    public String getFemale_LMP2() {
        return female_LMP2;
    }

    public void setFemale_LMP2(String female_LMP2) {
        this.female_LMP2 = female_LMP2;
    }

    public String getFemale_LMP3() {
        return female_LMP3;
    }

    public void setFemale_LMP3(String female_LMP3) {
        this.female_LMP3 = female_LMP3;
    }

    public String getFemale_Perineum() {
        return female_Perineum;
    }

    public void setFemale_Perineum(String female_Perineum) {
        this.female_Perineum = female_Perineum;
    }

    public String getFemale_FemaleOther() {
        return female_FemaleOther;
    }

    public void setFemale_FemaleOther(String female_FemaleOther) {
        this.female_FemaleOther = female_FemaleOther;
    }

    public String getCns_Normalcranialnerves() {
        return cns_Normalcranialnerves;
    }

    public void setCns_Normalcranialnerves(String cns_Normalcranialnerves) {
        this.cns_Normalcranialnerves = cns_Normalcranialnerves;
    }

    public String getCns_Normalcranialnervestxt() {
        return cns_Normalcranialnervestxt;
    }

    public void setCns_Normalcranialnervestxt(String cns_Normalcranialnervestxt) {
        this.cns_Normalcranialnervestxt = cns_Normalcranialnervestxt;
    }

    public String getCns_Gaitnormal() {
        return cns_Gaitnormal;
    }

    public void setCns_Gaitnormal(String cns_Gaitnormal) {
        this.cns_Gaitnormal = cns_Gaitnormal;
    }

    public String getCns_Gaitnormaltxt() {
        return cns_Gaitnormaltxt;
    }

    public void setCns_Gaitnormaltxt(String cns_Gaitnormaltxt) {
        this.cns_Gaitnormaltxt = cns_Gaitnormaltxt;
    }

    public String getCns_speechnormal() {
        return cns_speechnormal;
    }

    public void setCns_speechnormal(String cns_speechnormal) {
        this.cns_speechnormal = cns_speechnormal;
    }

    public String getCns_speechnormaltxt() {
        return cns_speechnormaltxt;
    }

    public void setCns_speechnormaltxt(String cns_speechnormaltxt) {
        this.cns_speechnormaltxt = cns_speechnormaltxt;
    }

    public String getCns_Coordinationnormal() {
        return cns_Coordinationnormal;
    }

    public void setCns_Coordinationnormal(String cns_Coordinationnormal) {
        this.cns_Coordinationnormal = cns_Coordinationnormal;
    }

    public String getCns_Coordinationnormaltxt() {
        return cns_Coordinationnormaltxt;
    }

    public void setCns_Coordinationnormaltxt(String cns_Coordinationnormaltxt) {
        this.cns_Coordinationnormaltxt = cns_Coordinationnormaltxt;
    }

    public String getCns_Powernormal() {
        return cns_Powernormal;
    }

    public void setCns_Powernormal(String cns_Powernormal) {
        this.cns_Powernormal = cns_Powernormal;
    }

    public String getCns_Powernormaltxt() {
        return cns_Powernormaltxt;
    }

    public void setCns_Powernormaltxt(String cns_Powernormaltxt) {
        this.cns_Powernormaltxt = cns_Powernormaltxt;
    }

    public String getCns_Sensorynormal() {
        return cns_Sensorynormal;
    }

    public void setCns_Sensorynormal(String cns_Sensorynormal) {
        this.cns_Sensorynormal = cns_Sensorynormal;
    }

    public String getCns_Sensorynormaltxt() {
        return cns_Sensorynormaltxt;
    }

    public void setCns_Sensorynormaltxt(String cns_Sensorynormaltxt) {
        this.cns_Sensorynormaltxt = cns_Sensorynormaltxt;
    }

    public String getMuskoskel_cervicalspinetenderness() {
        return muskoskel_cervicalspinetenderness;
    }

    public void setMuskoskel_cervicalspinetenderness(String muskoskel_cervicalspinetenderness) {
        this.muskoskel_cervicalspinetenderness = muskoskel_cervicalspinetenderness;
    }

    public String getMuskoskel_thoracicspinetenderness() {
        return muskoskel_thoracicspinetenderness;
    }

    public void setMuskoskel_thoracicspinetenderness(String muskoskel_thoracicspinetenderness) {
        this.muskoskel_thoracicspinetenderness = muskoskel_thoracicspinetenderness;
    }

    public String getMuskoskel_lumbosacralspinetenderness() {
        return muskoskel_lumbosacralspinetenderness;
    }

    public void setMuskoskel_lumbosacralspinetenderness(String muskoskel_lumbosacralspinetenderness) {
        this.muskoskel_lumbosacralspinetenderness = muskoskel_lumbosacralspinetenderness;
    }

    public String getMuskoskel_SIJtenderness() {
        return muskoskel_SIJtenderness;
    }

    public void setMuskoskel_SIJtenderness(String muskoskel_SIJtenderness) {
        this.muskoskel_SIJtenderness = muskoskel_SIJtenderness;
    }

    public String getMuskoskel_Swollenlargejoints() {
        return muskoskel_Swollenlargejoints;
    }

    public void setMuskoskel_Swollenlargejoints(String muskoskel_Swollenlargejoints) {
        this.muskoskel_Swollenlargejoints = muskoskel_Swollenlargejoints;
    }

    public String getMuskoskel_Tenderlargejoints() {
        return muskoskel_Tenderlargejoints;
    }

    public void setMuskoskel_Tenderlargejoints(String muskoskel_Tenderlargejoints) {
        this.muskoskel_Tenderlargejoints = muskoskel_Tenderlargejoints;
    }

    public String getMuskoskel_Swollensmalljoints() {
        return muskoskel_Swollensmalljoints;
    }

    public void setMuskoskel_Swollensmalljoints(String muskoskel_Swollensmalljoints) {
        this.muskoskel_Swollensmalljoints = muskoskel_Swollensmalljoints;
    }

    public String getMuskoskel_Tendersmalljoints() {
        return muskoskel_Tendersmalljoints;
    }

    public void setMuskoskel_Tendersmalljoints(String muskoskel_Tendersmalljoints) {
        this.muskoskel_Tendersmalljoints = muskoskel_Tendersmalljoints;
    }

    public String getMuskoskel_Chestwallribtenderness() {
        return muskoskel_Chestwallribtenderness;
    }

    public void setMuskoskel_Chestwallribtenderness(String muskoskel_Chestwallribtenderness) {
        this.muskoskel_Chestwallribtenderness = muskoskel_Chestwallribtenderness;
    }

    public String getMuskoskel_Upperlimbtenderness() {
        return muskoskel_Upperlimbtenderness;
    }

    public void setMuskoskel_Upperlimbtenderness(String muskoskel_Upperlimbtenderness) {
        this.muskoskel_Upperlimbtenderness = muskoskel_Upperlimbtenderness;
    }

    public String getMuskoskel_Lowerlimbtenderness() {
        return muskoskel_Lowerlimbtenderness;
    }

    public void setMuskoskel_Lowerlimbtenderness(String muskoskel_Lowerlimbtenderness) {
        this.muskoskel_Lowerlimbtenderness = muskoskel_Lowerlimbtenderness;
    }
    
    
    
}
