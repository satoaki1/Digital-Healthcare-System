# Digital Healthcare System

## Introduction
This is the java Project to create the Digital Healthcare Record System under several development constraints.
</br>
</br>

## Objective
The objectives of this project are to enable us to
1.	Apply problem-solving skills to evaluate and solve specific topics in advanced object-oriented problem and programs.
2.	Demonstrate capability to interact positively within a peer group, consider other viewpoints, and foster stable and harmonious relationships in solving computational problems related to object-oriented language.
3.	Present the outcome of the program developed using object-oriented language with the appropriate integrated environment.
</br>

## Scenario

> Covid-19 struck in early 2020 and surprised the world with its rapid and widespread transmission, high mortality rate, and lack of effective treatments and vaccines. In the beginning phase, many countries underestimated the outbreak's severity and did not implement swift actions to contain its spread. As the virus continued to spread rapidly, governments and health authorities worldwide began to implement strict safety measures, such as lockdowns, travel restrictions, and social distancing, to slow the spread of the virus and prevent healthcare systems from being overwhelmed. However, the global healthcare systems were unprepared for such a large-scale pandemic, and eventually, the healthcare systems in some countries quickly collapsed.
> 
> The pandemic had a profound impact on public health, the economy, and social life. Many countries were experiencing high numbers of infections, hospitalizations, and deaths. The situation persisted for many months as the virus spread globally until the covid-19 vaccines were discovered.
>
> The devastating effect of Covid-19 triggers the medical industry to respond rapidly with better, faster systems that could detect and contain the outbreak.
> 
> The Covid-19 pandemic inadvertently created a new opportunity for a new normal for digital health, among them the introduction of many digital systems that could potentially improve the quality of life, such as integrated **Electronic Medical Records (EMR)** and **Electronic Health Records (EHR)**, infectious disease tracking and many more.
</br>

## Functions

We are required to create a simple Electronic Medical Record (EMR) System to keep track of patients’ medical history. For simplicity, we will focus on the patient’s history regarding analysis, diagnosis, and treatment only. In this project, our team is required to use an object-oriented approach to model the five main classes, their subclasses (if any) and their corresponding attributes and methods:
1.	Patients (mandatory)
2.	Medical History (mandatory)
3.	Treatment Course (mandatory)
4.	Analysis
5.	Diagnosis
6.	Procedure and Medicine
     
The intended system should maximize the implementation of object-oriented concepts such as instantiation, encapsulation, inheritance, and polymorphism. Below is the UML skeleton class diagram for a broad idea of the class relationship:

![sample of class relationships in Electronic Medical Record (EMR) System](UMLofDigitalHealthcareSystem.png)

The following are the basic specifications for the system (non-exhaustive):
1.	The system is created using 5 forms: Patient Form, Medical History Form, Treatment Course Form, and two more forms using Java FX/Swing Application.
2.	The Patient Form allows personal particulars of patients such as name, national id, age, gender, address, contact number etc. to be read and saved to an external file named “patients.txt”.
3.	The Medical History Form contains the treatment history of the patient, including date, time, ward, treatment results, observation, major complications, attending doctor/nurse etc.
4.	The Treatment Course contains the start date and end date of a particular treatment.
5.	The Analysis Form contains the test information, including date, type of test, result etc.
6.	The Diagnosis Form contains diagnosis outcome such as date, name, diagnosed sickness etc.
7.	The Procedure and Medicine Form contains date time, procedure type, medication, amount, frequency etc.
8.  Your application has full support for Create, Read, Update and Delete (CRUD), i.e., the basic
   operations of persistent storage. 
9.  The candidates.txt content is a serialized data file.
