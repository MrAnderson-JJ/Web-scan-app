import React, { useEffect, useState } from "react";
import { fetchUserScans } from "@/api/userApi";
import { useKeycloak } from "@react-keycloak/web";
import { UserScan } from "@/types";
import UserScansTable from "./UserScansTable";

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

  return (
    <div>
      <div>HistoryPage</div>
      <div>{userScans && <UserScansTable userScans={userScans} />}</div>
    </div>
  );
};

export default HistoryPage;
