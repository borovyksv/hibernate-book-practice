@org.hibernate.annotations.GenericGenerator(
  name = "ID_GENERATOR",
  strategy = "enhanced-sequence",
  parameters = {
     @org.hibernate.annotations.Parameter(
        name = "sequence_name",
        value = "BOROVYK_SEQUENCE"
     ),
     @org.hibernate.annotations.Parameter(
        name = "initial_value",
        value = "9999"
     )
})
package com.borovyksv.model.helloworld;
