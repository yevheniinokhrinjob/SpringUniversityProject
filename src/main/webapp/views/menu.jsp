<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<table width="100%" border="1" class="table_blur">
    <tr >
        <th  align="center" class="tdClass2"><a href="http://localhost:8080/">Main</a></th>
        <th   align="center"><a href="/doctors.html">Doctors</a></th>
<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_DOCTOR')">
        <th border="1"  align="center"><a href="/myprofile.html">Profile</a></th>
</sec:authorize>
        <th border="1"  align="center"><a href="/contacts.html">Contacts</a></th>
    </tr>
</table>
