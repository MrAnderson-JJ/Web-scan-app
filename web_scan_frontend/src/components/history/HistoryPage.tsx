import { useEffect, useState } from "react";
import { fetchUserScans } from "@/api/userApi";
import { useKeycloak } from "@react-keycloak/web";
import { FilterScansDto, UserScan } from "@/types";
import UserScansTable from "./UserScansTable";
import FilterUserScansTableForm from "./FilterUserScansTableForm";
import { fetchFilteredScans } from "../../api/userApi";

const HistoryPage = () => {
  const { keycloak } = useKeycloak();
  const [userScans, setUserScans] = useState<UserScan[]>([]);

  useEffect(() => {
    const loadUserScans = async () => {
      try {
        if (keycloak.subject) {
          const data = await fetchUserScans(keycloak.subject);
          setUserScans(data);
          console.log(data);
        }
      } catch (error) {
        console.error("Failed to load user scans.");
      }
    };
    loadUserScans();
  }, []);

  const handleSubmit = async (result: FilterScansDto) => {
    let filteredScans = await fetchFilteredScans(result);
    setUserScans(filteredScans);
  };

  return (
    <div>
      <div>HistoryPage</div>
      <div>{<FilterUserScansTableForm onFilter={handleSubmit} />}</div>
      <div>{userScans && <UserScansTable userScans={userScans} />}</div>
    </div>
  );
};

export default HistoryPage;
