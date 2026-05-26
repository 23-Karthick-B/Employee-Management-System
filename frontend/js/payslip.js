async function downloadPayslip() {

  try {
    const token = localStorage.getItem("token");

    const res = await fetch( 
      "http://localhost:8080/employee/me",
      {
        headers: { Authorization: `Bearer ${token}` }
      }
    );

    if (!res.ok) {
      throw new Error( "Failed to load employee data" );
    }

    const emp = await res.json();
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();
    const basic = emp.salary * 0.75;
    const hra = emp.salary * 0.07;
    const allowance = emp.salary * 0.03;
    const pf = emp.salary * 0.08;
    const tax = emp.salary * 0.07;
    const net = emp.salary - pf - tax;

    doc.setFontSize(22);
    doc.text( "Employee Payslip",70,20 );
    doc.setFontSize(10);
    doc.text( "Employee Management System",20, 35);
    doc.text(`Month: ${new Date().toLocaleString( "default", { month: "long", year: "numeric" })}`,145, 35);

    doc.setFontSize(14);
    doc.text( "Employee Details", 20, 55);
    doc.setFontSize(11);
    doc.text( `Name: ${emp.name}`,20, 70);
    doc.text(`Email: ${emp.email}`, 20,80);
    doc.text( `Department: ${emp.department}`,20,90);
    doc.setFontSize(14);
    doc.text( "Salary Breakdown",20, 115);
    doc.setFontSize(11);

    let y = 130;
    const rows = [
      ["Basic Salary", basic],
      ["House Rent Allowance", hra],
      ["Allowance", allowance],
      ["PF Deduction", -pf],
      ["Tax Deduction", -tax],
      ["Net Salary", net]
    ];

    rows.forEach(row => {
      doc.text( row[0],20, y);
      doc.text( `Rs. ${row[1].toFixed(2)}`, 140, y);
      y += 12;
    });

    doc.setFontSize(9);
    doc.text(  "This is a system generated payslip.",20,270 );
    doc.save( `${emp.name}_Payslip.pdf` );

  } catch (err) {
    alert(err.message);
  }
}