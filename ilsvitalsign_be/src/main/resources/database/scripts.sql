





CREATE TABLE ilsvitalsigns_be.source (
  source_id SERIAL NOT NULL,
  source_name character varying(255),
  PRIMARY KEY (source_id)
);



CREATE TABLE ilsvitalsigns_be.value_quantity (
  value_quantity_id SERIAL NOT NULL ,
  code character varying(255) DEFAULT NULL,
  system_name character varying(255) DEFAULT NULL,
  unit character varying(255) DEFAULT NULL,
  value bigint DEFAULT NULL,
  PRIMARY KEY (value_quantity_id)
) ;

CREATE TABLE ilsvitalsigns_be.code (
  code_id SERIAL NOT NULL ,
  text character varying(255) DEFAULT NULL,
  PRIMARY KEY (code_id)
);



CREATE TABLE ilsvitalsigns_be.resource (
  resource_id bigint NOT NULL ,
  device_reference character varying(255) DEFAULT NULL,
  effective_date_time character varying(255) DEFAULT NULL,
  id character varying(255) DEFAULT NULL,
  resource_type character varying(255) DEFAULT NULL,
  status character varying(255) DEFAULT NULL,
  subject_refference character varying(255) DEFAULT NULL,
  code_code bigint DEFAULT NULL,
  meta_code bigint DEFAULT NULL,
  value_quantity_code bigint DEFAULT NULL,
  PRIMARY KEY (resource_id),
  FOREIGN KEY 		(code_code) 
	REFERENCES ilsvitalsigns_be.code (code_id) 
   	ON DELETE RESTRICT
    	ON UPDATE RESTRICT,
FOREIGN KEY 		(value_quantity_code) 
  REFERENCES ilsvitalsigns_be.value_quantity (value_quantity_id) 
  ON DELETE RESTRICT
    	ON UPDATE RESTRICT
);


CREATE TABLE ilsvitalsigns_be.category (
  category_id bigint NOT NULL ,
  resource_id bigint DEFAULT NULL,
  PRIMARY KEY (category_id),
  FOREIGN KEY 		(resource_id) 
  REFERENCES ilsvitalsigns_be.resource (resource_id) 
  ON DELETE RESTRICT
    	ON UPDATE RESTRICT
);




CREATE TABLE ilsvitalsigns_be.code_value (
  code_value_id bigint NOT NULL ,
  code varchar(20) NOT NULL,
  display character varying(255) NOT NULL,
  system_name character varying(255) DEFAULT NULL,
  category_id bigint DEFAULT NULL,
  code_id bigint DEFAULT NULL,
  PRIMARY KEY (code_value_id),
   FOREIGN KEY 		(category_id) 
  REFERENCES ilsvitalsigns_be.category (category_id) 
  ON DELETE RESTRICT
    	ON UPDATE RESTRICT,
  FOREIGN KEY 		(code_id)
  REFERENCES ilsvitalsigns_be.code (code_id) 
  ON DELETE RESTRICT
    	ON UPDATE RESTRICT

);


CREATE TABLE ilsvitalsigns_be.component (
  component_id bigint NOT NULL ,
  code_code bigint DEFAULT NULL,
  resource_id bigint DEFAULT NULL,
  value_quantity_code bigint DEFAULT NULL,
  PRIMARY KEY (component_id),
    FOREIGN KEY 		(code_code)
  REFERENCES ilsvitalsigns_be.code (code_id) 
  ON DELETE RESTRICT
    	ON UPDATE RESTRICT,
 FOREIGN KEY 		(resource_id)
  REFERENCES ilsvitalsigns_be.resource (resource_id) 
  ON DELETE RESTRICT
    	ON UPDATE RESTRICT,
  FOREIGN KEY 		(value_quantity_code)
  REFERENCES ilsvitalsigns_be.value_quantity (value_quantity_id)  
  ON DELETE RESTRICT
    	ON UPDATE RESTRICT
);


CREATE TABLE ilsvitalsigns_be.patient_enrollment (
  patient_id bigint NOT NULL ,
  ssn_number character varying(255) DEFAULT NULL,
  alt_pid character varying(255) DEFAULT NULL,
  audit_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  birth_order character varying(255) DEFAULT NULL,
  birth_place character varying(255) DEFAULT NULL,
  business_phone_number character varying(255) DEFAULT NULL,
  citizenship character varying(255) DEFAULT NULL,
  city character varying(255) DEFAULT NULL,
  country character varying(255) DEFAULT NULL,
  country_code character varying(255) DEFAULT NULL,
  dob character varying(255) DEFAULT NULL,
  driver_license_number character varying(255) DEFAULT NULL,
  enroll_flag boolean DEFAULT NULL,
  ethnic_group character varying(255) DEFAULT NULL,
  first_name character varying(255) DEFAULT NULL,
  gender character varying(255) DEFAULT NULL,
  home_phone_number character varying(255) DEFAULT NULL,
  last_name character varying(255) DEFAULT NULL,
  marital_status character varying(255) DEFAULT NULL,
  mid_name character varying(255) DEFAULT NULL,
  mother_identifier character varying(255) DEFAULT NULL,
  mother_maiden_name character varying(255) DEFAULT NULL,
  mrn character varying(255) DEFAULT NULL,
  multiple_birth_indicator character varying(255) DEFAULT NULL,
  nationality character varying(255) DEFAULT NULL,
  other character varying(255) DEFAULT NULL,
  patient_account_number character varying(255) DEFAULT NULL,
  patient_alias_first_name character varying(255) DEFAULT NULL,
  patient_alias_lastname character varying(255) DEFAULT NULL,
  patient_alias_mid_name character varying(255) DEFAULT NULL,
  patient_alias_prefix character varying(255) DEFAULT NULL,
  patient_alias_suffix character varying(255) DEFAULT NULL,
  patient_death_date_and_time character varying(255) DEFAULT NULL,
  patient_death_indicator character varying(255) DEFAULT NULL,
  patient_ext_id character varying(255) DEFAULT NULL,
  patient_set_id character varying(255) DEFAULT NULL,
  prefix character varying(255) DEFAULT NULL,
  primary_language character varying(255) DEFAULT NULL,
  race character varying(255) DEFAULT NULL,
  religion character varying(255) DEFAULT NULL,
  state character varying(255) DEFAULT NULL,
  street character varying(255) DEFAULT NULL,
  suffix character varying(255) DEFAULT NULL,
  type character varying(255) DEFAULT NULL,
  veterans_militar_ystatus character varying(255) DEFAULT NULL,
  zipcode character varying(255) DEFAULT NULL,
  PRIMARY KEY (patient_id)
);


CREATE TABLE ilsvitalsigns_be.source_observation (
  source_id bigint NOT NULL,
  patient_id bigint NOT NULL,
  PRIMARY KEY (patient_id,source_id),
   FOREIGN KEY 		(source_id)
  REFERENCES ilsvitalsigns_be.source (source_id)  
  ON DELETE RESTRICT
    	ON UPDATE RESTRICT
);




CREATE TABLE ilsvitalsigns_be.entry (
  entry_id bigint NOT NULL ,
  audit_timestamp timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  full_url character varying(255) DEFAULT NULL,
  mode character varying(255) DEFAULT NULL,
  patient_id bigint DEFAULT NULL,
  observation_id bigint DEFAULT NULL,
  resource_code bigint DEFAULT NULL,
  source_id bigint DEFAULT NULL,
  PRIMARY KEY (entry_id),
   FOREIGN KEY 		(patient_id)
  REFERENCES ilsvitalsigns_be.patient_enrollment (patient_id)  
  ON DELETE RESTRICT
    	ON UPDATE RESTRICT,
 FOREIGN KEY 		(resource_code)
  REFERENCES ilsvitalsigns_be.resource (resource_id)  
  ON DELETE RESTRICT
    	ON UPDATE RESTRICT,
FOREIGN KEY 		(source_id)
  REFERENCES ilsvitalsigns_be.source (source_id)  
  ON DELETE RESTRICT
    	ON UPDATE RESTRICT
);


INSERT INTO ilsvitalsigns_be.code_value (code_value_id, code, display) VALUES ('1', 'MR', 'Medical Record Number');
INSERT INTO ilsvitalsigns_be.code_value (code_value_id, code, display) VALUES ('2', 'SB', 'Social Security Number');
INSERT INTO ilsvitalsigns_be.code_value (code_value_id, code, display) VALUES ('3', 'S', 'Never Married');
INSERT INTO ilsvitalsigns_be.code_value (code_value_id, code, display) VALUES ('4', 'it', 'Italian');









