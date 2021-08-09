{
        "responseCode": "200",
        "responseMessage": "Recode Fetch Successfully!",
        "responseTime": "10:22",
        "employeesList": [
        {
        "empId": "1",
        "empName": "Keshav",
        "empFatherName": "Mr Ramesh Chand Gera",
        "empSalary": "9654267338",
        "empDesignation": "Sr. Java Developer",
        "leaveBalance": "3",
        "pfBalance": "60,000",
        "pfAccountNo.": "12345678"
        },
        {
        "empId": "2",
        "empName": "Ram",
        "empFatherName": "Mr Dasrath ji",
        "empSalary": "9999999999",
        "empDesignation": "Sr. Java Developer",
        "leaveBalance": "3",
        "pfBalance": "60,000",
        "pfAccountNo.": "12345678"
        },
        {
        "empId": "3",
        "empName": "Manisha",
        "empFatherName": "Mr Ramesh Chand Gera",
        "empSalary": "8826420999",
        "empDesignation": "BusinessMan",
        "leaveBalance": "3",
        "pfBalance": "60,000",
        "pfAccountNo.": "12345678"
        },
        {
        "empId": "4",
        "empName": "Happy",
        "empFatherName": "Mr Ramesh Chand Gera",
        "empSalary": "9582401701",
        "empDesignation": "Two Wheeler",
        "leaveBalance": "3",
        "pfBalance": "60,000",
        "pfAccountNo.": "12345678"
        },
        {
        "empId": "5",
        "empName": "Ritu",
        "empFatherName": "Mr Keshav Gera",
        "empSalary": "8888888888",
        "empDesignation": "Sararat Vibhag",
        "leaveBalance": "3",
        "pfBalance": "60,000",
        "pfAccountNo.": "12345678"
        }
        ]
        }


@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);


        emp_recycler_view = (RecyclerView) findViewById(R.id.emp_recycler_view);

        emp_recycler_view.setLayoutManager(new LinearLayoutManager(EmployeeActivity.this,
        LinearLayoutManager.VERTICAL, false));
        emp_recycler_view.setItemAnimator(new DefaultItemAnimator());

        employeeAdapter = new EmployeeAdapter(EmployeeActivity.this , employeeModelArrayList);

        emp_recycler_view.setAdapter(employeeAdapter);

        getJsonFileFromLocally();
        }





        private void getJsonFileFromLocally() {
        try {

        JSONObject jsonObject = new JSONObject(loadJSONFromAsset());

        JSONArray jsonArray = jsonObject.getJSONArray("employeesList");                  //TODO pass array object name


        for (int i = 0; i < jsonArray.length(); i++)
        {
        EmployeeModel employeeModel = new EmployeeModel();
        JSONObject jsonObjectEmployee = jsonArray.getJSONObject(i);
        String empId = jsonObjectEmployee.getString("empId");
        String empName = jsonObjectEmployee.getString("empName");
        String empDesignation = jsonObjectEmployee.getString("empDesignation");
        String empSalary = jsonObjectEmployee.getString("empSalary");
        String empFatherName = jsonObjectEmployee.getString("empFatherName");
        employeeModel.setEmpId(""+empId);
        employeeModel.setEmpName(""+empName);
        employeeModel.setEmpDesignation(""+empDesignation);
        employeeModel.setEmpSalary(""+empSalary);
        employeeModel.setEmpFatherNamer(""+empFatherName);

        employeeModelArrayList.add(employeeModel);

        }       // for

        if(employeeModelArrayList!=null) {
        employeeAdapter.dataChanged(employeeModelArrayList);
        }
        } catch (JSONException e) {
        e.printStackTrace();
        }
        }